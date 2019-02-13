#include "FuncTools.h"
#include <string>
#include <fstream>
#include <iostream>
#include <dirent.h>
using namespace std;






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

Fan loadFan(const string &dir, const string &fan_name){
	string user_file = dir + "\\" + fan_name + ".txt";
	string weibo_file = dir + "\\" + fan_name + "_Weibo.txt";
	ifstream ufin(user_file.c_str());
	if (!ufin){
		cout <<"user profile not found: " <<fan_name <<endl;
		return Fan();
	}
	string user;
	getline(ufin, user);
	Fan f(user);
	ufin.close();
	ifstream wfin(weibo_file.c_str());
	if (!wfin){
		cout <<"user status file not found: " <<fan_name <<endl;
		return Fan();
	}
	while (!wfin.eof()){
		string line;
		getline(wfin, line);
		if (!line.empty()){
			Status s(line);
			f.addStatus(s);
		}
	}
	wfin.close();
	f.comFeature();
	f.checkZombie();
	return f;
}


void zombieFansFilter(const string &dir, const string &res_dir){
	string output = res_dir+"\\ZombieType.res";
	ofstream fout(output.c_str());
	vector<string> file_list = get_filelist(dir);
	for (int i=0; i<file_list.size(); i++){
		if (file_list[i] != "." && file_list[i] != ".." && file_list[i].find("_Weibo") == -1){
			string name = file_list[i].substr(0, file_list[i].find_last_of("."));
			Fan f = loadFan(dir, name);
			if (!f.getName().empty()){
				fout <<f.getName() <<"\t" <<f.getIsZombie() <<endl;
			}
		}
	}
	cout <<"process_finished" <<endl;
	fout.close();
}