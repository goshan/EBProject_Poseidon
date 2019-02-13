#include <string>
#include <map>
#include <set>
#include <vector>
#include <iostream>
#include <fstream>
#include <sstream>
#include <algorithm>
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

map<string, int> zombieType;
map<string, vector<Pair> > fans;
vector<Pair> target;








void loadTarget(const string &file){
	ifstream fin(file.c_str());
	if (!fin){
		cout <<"POI file not found" <<endl;
		exit(0);
	}
	while (!fin.eof()){
		string line;
		getline(fin, line);
		if (!line.empty()){
			string word = line.substr(0, line.find("\t"));
			string weight_str = line.substr(line.find("\t")+1);
			double weight = 0.0;
			stringstream ss(weight_str.c_str());
			ss >>weight;
			target.push_back(make_pair(word, weight));
		}
	}
	fin.close();
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
	/*�õ���ԭsememe�ı���*/ 
	WORD wSememeCode = api.HowNet_Get_Sememe_Code(sememe.c_str());

	/*ѭ���õ���Ԫsememe��������λ��Ԫ*/
	vector<WORD> wHypCode;
	wHypCode.push_back(wSememeCode);
	WORD temp = wSememeCode;
	while((temp = api.HowNet_Get_Sememe_Hyp(temp)) != 0xffff ){
		wHypCode.push_back(temp);
	}
	for (int i=0; i<wHypCode.size(); i++){
		/*�õ�����ΪwHpyCode��Ԫ���ַ���*/ 
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

void getSimilarity(const string &resource, string output){
	output += "\\Similarity.res";
	ifstream fin(resource.c_str());
	ofstream fout(output.c_str());
	if (!fin){
		cout <<"resource file not found" <<endl;
		exit(0);
	}
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
				double sim = comTopicSim(target, topic);
				fout <<key <<"\t" <<sim <<endl;
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
	fout.close();
}