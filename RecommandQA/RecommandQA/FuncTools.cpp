#include <string>
#include <map>
#include <set>
#include <vector>
#include <iostream>
#include <fstream>
#include <sstream>
#include <algorithm>
#include "ICTCLAS50.h"
#include "HowNet2008.h"
using namespace std;






typedef pair<string, double> Pair;
int cmp(const Pair &x, const Pair &y){
	return x.second > y.second;
}



//Hownet veriable
CHOWNET2008_API api;
S_SEARCH_MODE s_mode;
char in[10];
char out[2048];

set <string> stopwords;






void switchSegResource(bool isOpen){
	if (isOpen){
		if(!ICTCLAS_Init()){ //初始化分词组件
			cout <<"Segment Base init failed" <<endl;  
			exit(0);
		}
		else {
			//设置词性标注集(0 计算所二级标注集，1 计算所一级标注集，2 北大二级标注集，3 北大一级标注集)
			ICTCLAS_SetPOSmap(2);
		}
	}
	else {
		ICTCLAS_Exit();	//释放资源退出
	}
}

void switchStopWords(bool isOpen){
	if (isOpen){
		string file = "StopWords.db";
		ifstream fin(file.c_str());
		if (!fin){
			cout <<"StopWords Base init failed" <<endl;
			exit(0);
		}
		else {
			while (!fin.eof()){
				string line;
				getline(fin, line);
				if (!line.empty()){
					stopwords.insert(line);
				}
			}
		}
		fin.close();
	}
	else {
		stopwords.clear();
	}
	
}



string wordSeg(const string &s){
	unsigned int len = s.length();
	char* res = (char *)malloc(len*6);
	int res_len = ICTCLAS_ParagraphProcess(s.c_str(), len, res, CODE_TYPE_UNKNOWN, 1);
	string res_str(res);
	return res_str;
}

vector<Pair> stopWordRemove(string s){
	vector<Pair> res;
	while (!s.empty()){
		string word = s.substr(0, s.find(" "));
		s = s.substr(s.find(" ")+1);
		if (!word.empty()){
			string type = word.substr(word.find_last_of("/"));
			word = word.substr(0, word.find_last_of("/"));
			if (!stopwords.count(word) && (type == "/n" || type == "/vn")){
				res.push_back(make_pair(word, 1.0));
			}
		}
	}
	return res;
}

//====================================================

void loadHowNet(){
	if (!api.HowNet_Initial()){
		cout <<"hownet init failed" <<endl;
		exit(0);
	}
	s_mode.language = HOWNET_LANGUAGE_CHINESE;
	s_mode.mode = HowNet_SearchMode_Exact;
}

int get_def(const string &word, vector<string> &def){
	set <string> tempSet;
	strcpy(in, word.c_str());
	WORD wCount = api.HowNet_Search_Keyword(in, s_mode);
	if (wCount != 0){
		DWORD *resID = api.HowNet_Get_SearchResult();
		for (WORD i=0; i<wCount; i++){
			api.HowNet_Get_Unit_Item(resID[i], HOWNET_ITEM_ID_ALL, out);
			string temp(out);
			int pos1 = -1;
			int pos2 = -1;
			pos1 = temp.find("DEF=")+5;
			pos2 = temp.find(":", pos1);
			if (pos2 < 0)
				pos2 = temp.find("}", pos1);
			string res = temp.substr(pos1, pos2-pos1);
			tempSet.insert(res);
		}
	}
	for (set<string> ::iterator it=tempSet.begin(); it!=tempSet.end(); it++)
		def.push_back(*it);
	return wCount;
}


void getParents(const string &sememe, vector<string> &parents){
	/*得到义原sememe的编码*/ 
	WORD wSememeCode = api.HowNet_Get_Sememe_Code(sememe.c_str());

	/*循环得到义元sememe的所有上位义元*/
	vector<WORD> wHypCode;
	wHypCode.push_back(wSememeCode);
	WORD temp = wSememeCode;
	while((temp = api.HowNet_Get_Sememe_Hyp(temp)) != 0xffff ){
		wHypCode.push_back(temp);
	}
	for (int i=0; i<wHypCode.size(); i++){
		/*得到编码为wHpyCode义元的字符串*/ 
		string pSememe(api.HowNet_Get_Sememe_String(wHypCode[i]));
		parents.push_back(pSememe);
	}
}

int comSememeDis(const string &src, const string &tar){
	if (src == tar){     //same meaning
		return 0;
	}
	vector<string> srcParents;
	getParents(src, srcParents);
	vector<string> tarParents;
	getParents(tar, tarParents);
	if (srcParents[srcParents.size()-1] != tarParents[tarParents.size()-1]){   //different meaning
		return 100;
	}
	else{    //like meaning
		bool samePath = false;
		int dis = 0;
		string sememe = "";
		vector<string> path;
		if (srcParents.size() < tarParents.size()){
			sememe = srcParents[0];
			path = tarParents;
		}
		else if (srcParents.size() > tarParents.size()){
			sememe = tarParents[0];
			path = srcParents;
		}
		for (int i=0; i<path.size(); i++){
			if (sememe == path[i]){
				samePath = true;
				dis = i;
				break;
			}
		}
		if (samePath){    //two meaning are on same branch
			return dis;
		}
		else {   //different branch
			int subRoot = 0;
			for (int i=0; i<min(srcParents.size(), tarParents.size()); i++){
				if (srcParents[srcParents.size()-i-1] != tarParents[tarParents.size()-i-1]){
					subRoot = i-1;
					break;
				}
			}
			return srcParents.size()+tarParents.size()-2-2*subRoot;
		}
	}
}

int comWordDis(const string &target, const string &source){
	vector<string> tarDef;
	vector<string> srcDef;
	get_def(target, tarDef);
	get_def(source, srcDef);

	int minDis = 100;
	for (int i=0; i<tarDef.size(); i++){
		for (int j=0; j<srcDef.size(); j++){
			int dis = comSememeDis(tarDef[i], srcDef[j]);
			if (dis < minDis)
				minDis = dis;
		}
	}
	return minDis;
}

double comTopicSim(const vector<Pair> &hot, const vector<Pair> &topicKeyWords){
	vector<double> res;
	for (int i=0; i<hot.size(); i++){
		for (int j=0; j<topicKeyWords.size(); j++){
			if (hot[i].first == topicKeyWords[j].first){
				res.push_back(hot[i].second*topicKeyWords[j].second);
			}
			else {
				int dis = comWordDis(hot[i].first, topicKeyWords[j].first)+1;
				res.push_back(hot[i].second*topicKeyWords[j].second*((double)1/dis));
			}
		}
	}
	sort(res.begin(), res.end());
	double sum = 0.0;
	int size = hot.size() < topicKeyWords.size() ? hot.size() : topicKeyWords.size();
	for (int i=0; i<size; i++){
		sum += res[i]*100000;
	}
	return sum;
}

double getSimilarity(const string &question, const string &connection, const string &output){
	ifstream fin(connection.c_str());
	ofstream fout(output.c_str());
	if (!fin){
		cout <<"connection file not found" <<endl;
		exit(0);
	}
	string seg = wordSeg(question);
	vector<Pair> q_words = stopWordRemove(seg);
	fout <<"####Question Hot Words####" <<endl;
	for (int i=0; i<q_words.size(); i++){
		fout <<q_words[i].first <<"\t" <<q_words[i].second <<endl;
	}
	fout <<"============================" <<endl;
	string key = "";
	int cnt = 0;
	vector<Pair> topic;
	while (!fin.eof()){
		string line;
		getline(fin, line);
		if (!line.empty()){
			if (line.find("####") != -1){
				key = line.substr(4);
				key = key.substr(0, key.find("####"));
				cnt = 0;
				topic.clear();
			}
			else if (line == "============================"){
				if (key != "." && key != ".."){
					double sim = comTopicSim(q_words, topic);
					fout <<key <<"\t" <<sim <<endl;
				}
			}
			else {
				if (cnt >= 5){
					continue;
				}
				string word = line.substr(0, line.find("/n"));
				stringstream ss(line.substr(line.find(" ")+1).c_str());
				double weight = 0.0;
				ss >>weight;
				topic.push_back(make_pair(word, weight));
				cnt ++;
			}
		}
	}
	fin.close();
	fout.close();
}