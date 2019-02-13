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
#include <windows.h>
#include "ICTCLAS50.h"
using namespace std;







typedef pair<string, double> Pair;
int cmp(const Pair &x, const Pair &y){
	return x.second > y.second;
}



vector<string> file_list;
set <string> stopwords;
vector <map <string, double> > tf;
map <string, double> idf;
vector <vector<Pair> > weight;








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



void getFilelist(const string &file, const string &res_dir){
	string output = res_dir + "\\tmp\\Text.cache";
	ifstream fin(file.c_str());
	ofstream fout(output.c_str());
	while (!fin.eof()){
		string line;
		getline (fin, line);
		if (!line.empty()){
			if (line.find("####") != -1){
				string file = line.substr(4);
				file = file.substr(0, file.find("####"));
				file_list.push_back(file);
			}
			else if (line.find("====") != -1){
				fout <<endl;
			}
			else {
				fout <<line <<endl;
			}
		}
	}
	fin.close();
	fout.close();
}

void wordSeg(const string &res_dir){
	string input = res_dir + "\\tmp\\Text.cache";
	string output = res_dir + "\\tmp\\Seg.cache";
	ICTCLAS_FileProcess(input.c_str(), output.c_str(), CODE_TYPE_GB, 1);
}

void stopWordRemove(const string &res_dir){
	string input = res_dir + "\\tmp\\Seg.cache";
	string output = res_dir + "\\tmp\\Core.cache";
	ifstream fin(input.c_str());
	ofstream fout(output.c_str());
	while (!fin.eof()){
		string line;
		getline (fin, line);
		if (!line.empty()){
			while (!line.empty()){
				string word = line.substr(0, line.find(" "));
				line = line.substr(line.find(" ")+1);
				if (!word.empty() && word.find("/") != -1){
					string type = word.substr(word.find_last_of("/"));
					word = word.substr(0, word.find_last_of("/"));
					if (!stopwords.count(word) && (type == "/n" || type == "/vn")){
						fout <<word + type <<endl;
					}
				}
			}
		}
		else {
			fout <<"===========" <<endl;
		}
	}
	fin.close();
	fout.close();
}

void genTF(const string &res_dir){
	string input = res_dir + "\\tmp\\Core.cache";
	ifstream fin(input.c_str());
	int word_sum = 0;
	map <string, int> cache;
	int index = 0;
	while (!fin.eof()){
		string line;
		getline(fin, line);
		if (!line.empty()){
			if (line != "==========="){
				if (!cache.count(line)){
					cache[line] = 0;
				}
				cache[line]++;
				word_sum ++;
			}
			else {
				map<string, double> temp_res;
				for (map<string, int> ::const_iterator it=cache.begin(); it!=cache.end(); it++){
					temp_res[it ->first] = (double)(it ->second)/word_sum;
				}
				word_sum = 0;
				cache.clear();
				tf.push_back(temp_res);
			}
		}
	}
	fin.close();
}

void genIDF(const string &res_dir){
	string input = res_dir + "\\tmp\\Core.cache";
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
	for (int i=0; i<tf.size(); i++){
		vector<Pair> temp_weight;
		for (map<string, double> ::const_iterator it=tf[i].begin(); it != tf[i].end(); it++){
			temp_weight.push_back(make_pair(it ->first, 1+it ->second*idf[it ->first]));
		}
		sort(temp_weight.begin(), temp_weight.end(), cmp);
		weight.push_back(temp_weight);
	}
}

void to_file(const string &res_dir){
	string output = res_dir + "\\Weight.res";
	ofstream fout(output.c_str());
	for (int i=0; i<file_list.size(); i++){
		fout <<"####" <<file_list[i] <<"####" <<endl;
		for (int j=0; j<weight[i].size(); j++){
			fout <<weight[i][j].first <<" " <<weight[i][j].second <<endl;
		}
		fout <<"============================" <<endl;
	}
	fout.close();
}


//************************************************//




//*****************external func*******************//

void extract(const string &file, const string &res_dir){
	if (switchSegResource(true) && switchStopWords(true)){
		getFilelist(file, res_dir);
		wordSeg(res_dir);
		stopWordRemove(res_dir);
		genTF(res_dir);
		genIDF(res_dir);
		genWeight();
		to_file(res_dir);
		cout <<"process_finished" <<endl;
	}
	switchSegResource(false);
	switchStopWords(false);
}


//************************************************//