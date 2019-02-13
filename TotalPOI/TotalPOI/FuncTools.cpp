#include <string>
#include <map>
#include <set>
#include <vector>
#include <iostream>
#include <fstream>
#include <sstream>
#include <algorithm>
using namespace std;






typedef pair<string, double> Pair;
int cmp(const Pair &x, const Pair &y){
	return x.second > y.second;
}


map<string, int> zombieType;
map<string, vector<Pair> > fans;
vector<Pair> finalTopics;







void loadZombieType(const string &file){
	ifstream fin(file.c_str());
	if (!fin){
		cout <<"zombie_type file not found" <<endl;
		exit(0);
	}
	while (!fin.eof()){
		string line;
		getline(fin, line);
		string name = line.substr(0, line.find("\t"));
		int type = atoi(line.substr(line.find("\t")+1).c_str());
		zombieType[name] = type;
	}
}

void loadFans(const string &file){
	ifstream fin(file.c_str());
	if (!fin){
		cout <<"fans file not found" <<endl;
		exit(0);
	}
	string key = "";
	int cnt = 0;
	while (!fin.eof()){
		string line;
		getline(fin, line);
		if (!line.empty()){
			if (line.find("####") != -1){
				key = line.substr(4);
				key = key.substr(0, key.find("####"));
				cnt = 0;
			}
			else if (line == "============================"){
			}
			else {
				if (cnt >= 5){
					continue;
				}
				string word = line.substr(0, line.find("/n"));
				stringstream ss(line.substr(line.find(" ")+1).c_str());
				double weight = 0.0;
				ss >>weight;
				fans[key].push_back(make_pair(word, weight));
				cnt ++;
			}
		}
	}
	fin.close();
}


void getFinalWords(){
	map<string, double> temp_map;
	for (map<string, vector<Pair> > ::const_iterator it = fans.begin(); it != fans.end(); it++){
		if (zombieType.count(it ->first) && zombieType[it ->first] == 0){
			for (int i=0; i<it ->second.size(); i++){
				if (!temp_map.count(it ->second[i].first)){
					temp_map[it ->second[i].first] = 0.0;
				}
				temp_map[it ->second[i].first] += it ->second[i].second;
			}
		}
	}
	vector<Pair> wholeWords;
	for (map<string, double> ::const_iterator it = temp_map.begin(); it != temp_map.end(); it ++){
		wholeWords.push_back(make_pair(it ->first, it ->second*100));
	}
	sort(wholeWords.begin(), wholeWords.end(), cmp);
	for (int i=0; i<10; i++){
		finalTopics.push_back(wholeWords[i]);
	}
}


void toFile(const string &res_dir){
	string output = res_dir + "\\Weight.res";
	ofstream fout(output.c_str());
	for (int i=0; i<finalTopics.size(); i++){
		fout <<finalTopics[i].first <<"\t" <<finalTopics[i].second <<endl;
	}
	fout.close();
}