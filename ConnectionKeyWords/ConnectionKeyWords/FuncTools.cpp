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
vector <map <string, double> > title_tf;
vector <map <string, double> > content_tf;
map <string, double> title_idf;
map <string, double> content_idf;
vector <vector<Pair> > title_weight;
vector <vector<Pair> > content_weight;







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




void genText(const string &connection_dir, const string &res_dir){
	string temp_dir = res_dir + "\\tmp";
	_mkdir(temp_dir.c_str());
	string output = temp_dir + "\\Text.cache";
	ofstream fout(output.c_str());

	file_list = get_filelist(connection_dir.c_str());
	for (int i=0; i<file_list.size(); i++){
		string input = connection_dir+"\\"+file_list[i];
		ifstream fin(input.c_str());
		string temp;
		getline(fin, temp);
		if (temp == "ch"){
			while (!fin.eof()){
				string line;
				getline(fin, line);
				if (!line.empty()){
					fout <<line <<endl;
				}
			}
		}
		fin.close();
		fout <<endl;
	}
	fout.close();
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
	int index = 0;
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
						fout <<word + type <<"\t" <<index <<endl;
					}
				}
			}
			index ++;
		}
		else {
			fout <<"===========" <<endl;
			index = 0;
		}
	}
	fin.close();
	fout.close();
}

void genTF(const string &dir){
	string input = dir + "\\Core.cache";
	ifstream fin(input.c_str());
	int title_word_sum = 0;
	int content_word_sum = 0;
	map <string, int> title_cache;
	map <string, int> content_cache;
	int index = 0;
	while (!fin.eof()){
		string line;
		getline(fin, line);
		if (!line.empty()){
			if (line != "==========="){
				string type = line.substr(line.find("\t")+1);
				line = line.substr(0, line.find("\t"));
				if (type == "0"){
					if (!title_cache.count(line)){
						title_cache[line] = 0;
					}
					title_cache[line]++;
					title_word_sum ++;
				}
				else {
					if (!content_cache.count(line)){
						content_cache[line] = 0;
					}
					content_cache[line]++;
					content_word_sum ++;
				}
			}
			else {
				map<string, double> temp_res;
				for (map<string, int> ::const_iterator it=title_cache.begin(); it!=title_cache.end(); it++){
					temp_res[it ->first] = (double)(it ->second)/title_word_sum;
				}
				title_word_sum = 0;
				title_cache.clear();
				title_tf.push_back(temp_res);

				temp_res.clear();
				for (map<string, int> ::const_iterator it=content_cache.begin(); it!=content_cache.end(); it++){
					temp_res[it ->first] = (double)(it ->second)/content_word_sum;
				}
				content_word_sum = 0;
				content_cache.clear();
				content_tf.push_back(temp_res);
			}
		}
	}
	fin.close();
}

void genIDF(const string &dir){
	string input = dir + "\\Core.cache";
	ifstream fin(input.c_str());
	int doc_sum = 0;
	set<string> title_app;
	set<string> content_app;
	map<string, int> title_cache;
	map<string, int> content_cache;
	while (!fin.eof()){
		string line;
		getline(fin, line);
		if (!line.empty()){
			if (line == "==========="){
				doc_sum ++;
				for (set<string> ::const_iterator it=title_app.begin(); it!=title_app.end(); it++){
					if (title_cache.count(*it)){
						title_cache[*it] ++;
					}
					else {
						title_cache[*it] = 1;
					}
				}
				title_app.clear();
				for (set<string> ::const_iterator it=content_app.begin(); it!=content_app.end(); it++){
					if (content_cache.count(*it)){
						content_cache[*it] ++;
					}
					else {
						content_cache[*it] = 1;
					}
				}
				content_app.clear();
			}
			else {
				string type = line.substr(line.find("\t")+1);
				line = line.substr(0, line.find("\t"));
				if (type == "0"){
					title_app.insert(line);
				}
				else {
					content_app.insert(line);
				}
			}
		}
	}
	fin.close();
	for (map<string, int> ::const_iterator it=title_cache.begin(); it!=title_cache.end(); it++){
		title_idf[it ->first] = log10((double)doc_sum/it ->second);
	}
	for (map<string, int> ::const_iterator it=content_cache.begin(); it!=content_cache.end(); it++){
		content_idf[it ->first] = log10((double)doc_sum/it ->second);
	}
}

void genWeight(){
	for (int i=0; i<title_tf.size(); i++){
		vector<Pair> temp_weight;
		for (map<string, double> ::const_iterator it=title_tf[i].begin(); it != title_tf[i].end(); it++){
			temp_weight.push_back(make_pair(it ->first, 1+it ->second*title_idf[it ->first]));
		}
		sort(temp_weight.begin(), temp_weight.end(), cmp);
		title_weight.push_back(temp_weight);
	}
	for (int i=0; i<content_tf.size(); i++){
		vector<Pair> temp_weight;
		for (map<string, double> ::const_iterator it=content_tf[i].begin(); it != content_tf[i].end(); it++){
			temp_weight.push_back(make_pair(it ->first, it ->second*content_idf[it ->first]));
		}
		sort(temp_weight.begin(), temp_weight.end(), cmp);
		content_weight.push_back(temp_weight);
	}
}

void to_file(const string &res_dir){
	string output = res_dir + "\\Weight.res";
	ofstream fout(output.c_str());
	for (int i=0; i<file_list.size(); i++){
		fout <<"####" <<file_list[i] <<"####" <<endl;
		for (int j=0; j<title_weight[i].size(); j++){
			fout <<title_weight[i][j].first <<" " <<title_weight[i][j].second <<endl;
		}
		for (int j=0; j<content_weight[i].size(); j++){
			fout <<content_weight[i][j].first <<" " <<content_weight[i][j].second <<endl;
		}
		fout <<"============================" <<endl;
	}
	fout.close();
}


//************************************************//




//*****************external func*******************//

void extract(const string &connection_dir, const string &res_dir){
	if (switchSegResource(true) && switchStopWords(true)){
		genText(connection_dir, res_dir);
		wordSeg(res_dir+"\\tmp");
		stopWordRemove(res_dir+"\\tmp");
		genTF(res_dir+"\\tmp");
		genIDF(res_dir+"\\tmp");
		genWeight();
		to_file(res_dir);
		cout <<"process_finished" <<endl;
	}
	switchSegResource(false);
	switchStopWords(false);
}


//************************************************//