#include "FuncTools.h"
#include <iostream>
#include <string>
#include <set>
#include <map>
#include <vector>
#include <fstream>
#include <algorithm>
#include <complex>
#include <direct.h>
#include <dirent.h>
#include "ICTCLAS50.h"
#include "Fans.h"
#include "Status.h"
using namespace std;







typedef pair<string, double> Pair;
int cmp(const Pair &x, const Pair &y){
	return x.second > y.second;
}

set <string> stopwords;
map <string, double> tf;
map <string, double> idf;
vector<Pair> weight;








vector<string> get_filelist(const string &dir_name){
	DIR *dir;
	struct dirent *ent;
	vector<string> file_list;
	if ((dir = opendir (dir_name.c_str())) != NULL) {
		while ((ent = readdir (dir)) != NULL) {
			file_list.push_back(ent ->d_name);
		}
		closedir (dir);
	}
	else {
		cout <<"dir not found" <<endl;
	}
	return file_list;
}

bool switchSegResource(bool isOpen){
	if (isOpen){
		if(!ICTCLAS_Init()){ //初始化分词组件
			cout <<"Segment Base init failed" <<endl;  
			return false;
		}
		else {
			//设置词性标注集(0 计算所二级标注集，1 计算所一级标注集，2 北大二级标注集，3 北大一级标注集)
			ICTCLAS_SetPOSmap(2);
			return true;
		}
	}
	else {
		ICTCLAS_Exit();	//释放资源退出
		return true;
	}
}

bool switchStopWords(bool isOpen){
	if (isOpen){
		string file = "StopWords.db";
		ifstream fin(file.c_str());
		if (!fin){
			cout <<"StopWords Base init failed" <<endl;
			return false;
		}
		else {
			while (!fin.eof()){
				string line;
				getline(fin, line);
				if (!line.empty()){
					stopwords.insert(line);
				}
			}
			return true;
		}
		fin.close();
	}
	else {
		stopwords.clear();
		return true;
	}
	
}




bool genStatusText(const string &fans_dir, const string &res_dir, const string &name){
	string temp_dir = res_dir + "\\tmp";
	_mkdir(temp_dir.c_str());
	string user_file = fans_dir + "\\" + name + ".txt";
	string weibo_file = fans_dir + "\\" + name + "_Weibo.txt";
	string txt_file = temp_dir + "\\" + "Text.cache";
	ofstream fout(txt_file.c_str());
	ifstream ufin(user_file.c_str());
	if (!ufin){
		cout <<"user profile not found: " <<name <<endl;
		return false;
	}
	string user;
	getline(ufin, user);
	Fan f(user);
	fout <<f.getDesc() <<endl;
	ufin.close();
	ifstream wfin(weibo_file.c_str());
	if (!wfin){
		cout <<"user status file not found: " <<name <<endl;
		return false;
	}
	while (!wfin.eof()){
		string line;
		getline(wfin, line);
		if (!line.empty()){
			Status s(line);
			fout <<(s.checkIsOriginal() ? s.getContent() : s.getRetweetedStatus().getContent()) <<endl;
		}
	}
	wfin.close();
	fout.close();
	return true;
}

void wordSeg(const string &dir){
	string input = dir + "\\Text.cache";
	string output = dir + "\\Seg.cache";
	ICTCLAS_FileProcess(input.c_str(), output.c_str(), CODE_TYPE_GB, 1);
}

void stopWordRemove(const string &dir){
	string input = dir + "\\Seg.cache";
	string output = dir + "\\Core.cache";
	ifstream fin(input.c_str());
	ofstream fout(output.c_str());
	while (!fin.eof()){
		string line;
		getline (fin, line);
		if (!line.empty()){
			while (!line.empty()){
				string word = line.substr(0, line.find(" "));
				line = line.substr(line.find(" ")+1);
				if (!word.empty()){
					string type = word.substr(word.find_last_of("/"));
					word = word.substr(0, word.find_last_of("/"));
					if (!stopwords.count(word) && (type == "/n" || type== "/vn")){
						fout <<word + type <<endl;
					}
				}
			}
			fout <<"===========" <<endl;
		}
	}
	fin.close();
	fout.close();
}

void genTF(const string &dir){
	string input = dir + "\\Core.cache";
	ifstream fin(input.c_str());
	int word_sum = 0;
	map <string, int> cache;
	while (!fin.eof()){
		string line;
		getline(fin, line);
		if (!line.empty() && line != "==========="){
			if (cache.count(line)){
				cache[line]++;
			}
			else {
				cache[line] = 1;
			}
			word_sum ++;
		}
	}
	fin.close();
	for (map<string, int> ::const_iterator it=cache.begin(); it!=cache.end(); it++){
		tf[it ->first] = (double)(it ->second)/word_sum;
	}
}

void genIDF(const string &dir){
	string input = dir + "\\Core.cache";
	ifstream fin(input.c_str());
	int doc_sum = 0;
	set<string> app;
	map<string, int> cache;
	while (!fin.eof()){
		string line;
		getline(fin, line);
		if (!line.empty()){
			if (line == "==========="){
				doc_sum ++;
				for (set<string> ::const_iterator it=app.begin(); it!=app.end(); it++){
					if (cache.count(*it)){
						cache[*it] ++;
					}
					else {
						cache[*it] = 1;
					}
				}
				app.clear();
			}
			else {
				app.insert(line);
			}
		}
	}
	fin.close();
	for (map<string, int> ::const_iterator it=cache.begin(); it!=cache.end(); it++){
		idf[it ->first] = log10((double)doc_sum/it ->second);
	}
}

void genWeight(){
	for (map<string, double> ::const_iterator it=tf.begin(); it!=tf.end(); it++){
		weight.push_back(make_pair(it ->first, it ->second*idf[it ->first]));
	}
	sort(weight.begin(), weight.end(), cmp);
}

void to_file(const string &output, const string &name){
	ofstream fout(output.c_str(), ios::app);
	fout <<"####" <<name <<"####" <<endl;
	for (int i=0; i<weight.size(); i++){
		fout <<weight[i].first <<" " <<weight[i].second <<endl;
	}
	fout <<"============================" <<endl;
	fout.close();
}


//************************************************//




//*****************external func*******************//

void extract(const string &fans_dir, const string &res_dir){
	if (switchSegResource(true) && switchStopWords(true)){
		string output = res_dir + "\\Weight.res";
		ofstream fout(output.c_str());
		fout.close();
		vector<string> file_list = get_filelist(fans_dir);
		for (int i=0; i<file_list.size(); i++){
			if (file_list[i] != "." && file_list[i] != ".." && file_list[i].find("_Weibo") == -1){
				string name = file_list[i].substr(0, file_list[i].find_last_of("."));
				tf.clear();
				idf.clear();
				weight.clear();
				if (!genStatusText(fans_dir, res_dir, name)){
					continue;
				}
				wordSeg(res_dir+"\\tmp");
				stopWordRemove(res_dir+"\\tmp");
				genTF(res_dir+"\\tmp");
				genIDF(res_dir+"\\tmp");
				genWeight();
				to_file(output, name);
			}
		}
		cout <<"process_finished" <<endl;
	}
	switchSegResource(false);
	switchStopWords(false);
}


//************************************************//