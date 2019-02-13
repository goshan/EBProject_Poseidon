#include "Fans.h"
#include <string>
#include <map>
#include <sstream>
#include <algorithm>
#include <iostream>
using namespace std;





//**********tools***************//

map<string, int> monthDict;

void initMonthDict(){
	monthDict["Jan"] = 0;
	monthDict["Feb"] = 31;
	monthDict["Mar"] = 59;
	monthDict["Apr"] = 90;
	monthDict["May"] = 120;
	monthDict["Jun"] = 151;
	monthDict["Jul"] = 181;
	monthDict["Aug"] = 212;
	monthDict["Sep"] = 243;
	monthDict["Oct"] = 273;
	monthDict["Nov"] = 304;
	monthDict["Dec"] = 334;
}

int conTime(string time){
	time = time.substr(time.find(" ")+1);
	string month_str = time.substr(0, time.find(" "));
	time = time.substr(time.find(" ")+1);
	string day_str = time.substr(0, time.find(" "));
	time = time.substr(time.find(" ")+1);
	string hour_str = time.substr(0, time.find(":"));
	time = time.substr(time.find(":")+1);
	string minute_str = time.substr(0, time.find(":"));
	time = time.substr(time.find(":")+1);
	string second_str = time.substr(0, time.find(" "));
	time = time.substr(time.find(" ")+1);
	string year_str = time.substr(time.find(" ")+1);

	int year = (atoi(year_str.c_str())-1970)*365*24*60*60;
	int month = (monthDict[month_str])*24*60*60;
	int day = (atoi(day_str.c_str())-1)*24*60*60;
	int hour = atoi(hour_str.c_str())*60*60;
	int minute = atoi(minute_str.c_str())*60;
	int second = atoi(second_str.c_str());
	
	return (year+month+day+hour+minute+second);
}



//*****************************//

Fan ::Fan(){
	this ->id = "";
	this ->name = "";
	this ->city = "";
	this ->desc = "";
	this ->gender = "";
	this ->followerCnt = 0;
	this ->followingCnt = 0;
	this ->statusCnt = 0;
	this ->favouritesCnt = 0;
	this ->createdAt = "";

	//feature
	this ->followerRate = 0.0;
	this ->statusStampAvg = 0;
	this ->RWStatusRate = 0.0;

	//Fan type
	this ->isZombie = false;
}

Fan ::Fan(const string &json){
	int start = 0;
	string key = "";
	string value = "";
	bool txt = true;
	for (int i=0; i<json.length(); i++){
		if (json[i] == '{'){
			start = i+1;
		}
		else if (json[i] == '='){
			key = json.substr(start, i-start);
			start = i+2;
		}
		else if (json[i] == '\"'){
			txt = txt ? false : true;
		}
		else if ((json[i] == ',' || json[i] == '}') && !txt){
			value = json.substr(start, i-start-2);
			if (key == "id"){
				this ->id = value;
			}
			else if (key == "screenName"){
				this ->name = value;
			}
			else if (key == "location"){
				this ->city = value;
			}
			else if (key == "description"){
				this ->desc = value;
			}
			else if (key == "gender"){
				this ->gender = value;
			}
			else if (key == "followersCount"){
				this ->followerCnt = atoi(value.c_str());
			}
			else if (key == "friendsCount"){
				this ->followingCnt = atoi(value.c_str());
			}
			else if (key == "statusesCount"){
				this ->statusCnt = atoi(value.c_str());
			}
			else if (key == "favouritesCount"){
				this ->favouritesCnt = atoi(value.c_str());
			}
			else if (key == "createdAt"){
				this ->createdAt = value;
			}
			start = i+2;
		}
	}

	//
	this ->followerRate = 0.0;
	this ->statusStampAvg = 0;
	this ->RWStatusRate = 0.0;

	this ->isZombie = false;
}

Fan ::Fan(const Fan &f){
	this ->id = f.id;
	this ->name = f.name;
	this ->city = f.city;
	this ->desc = f.desc;
	this ->gender = f.gender;
	this ->followerCnt = f.followerCnt;
	this ->followingCnt = f.followingCnt;
	this ->statusCnt = f.statusCnt;
	this ->favouritesCnt = f.favouritesCnt;
	this ->createdAt = f.createdAt;
	this ->statuses = f.statuses;

	//feature
	this ->followerRate = f.followerRate;
	this ->statusStampAvg = f.statusStampAvg;
	this ->RWStatusRate = f.RWStatusRate;
	//Fan type
	this ->isZombie = f.isZombie;
}

bool Fan ::operator = (const Fan &f){
	this ->id = f.id;
	this ->name = f.name;
	this ->city = f.city;
	this ->desc = f.desc;
	this ->gender = f.gender;
	this ->followerCnt = f.followerCnt;
	this ->followingCnt = f.followingCnt;
	this ->statusCnt = f.statusCnt;
	this ->favouritesCnt = f.favouritesCnt;
	this ->createdAt = f.createdAt;
	this ->statuses = f.statuses;

	//feature
	this ->followerRate = f.followerRate;
	this ->statusStampAvg = f.statusStampAvg;
	this ->RWStatusRate = f.RWStatusRate;
	//Fan type
	this ->isZombie = f.isZombie;

	return true;
}

string Fan ::getId() const{
	return this ->id;
}
string Fan ::getName() const{
	return this ->name;
}

string Fan ::getCity() const{
	return this ->city;
}

string Fan ::getDesc() const{
	return this ->desc;
}
string Fan ::getGender() const{
	return this ->gender;
}

int Fan ::getFollowerCnt() const{
	return this ->followerCnt;
}

int Fan ::getFollowingCnt() const{
	return this ->followingCnt;
}

int Fan ::getStatusCnt() const{
	return this ->statusCnt;
}

int Fan ::getFavouritesCnt() const{
	return this ->favouritesCnt;
}

string Fan ::getCreatedAt() const{
	return this ->createdAt;
}

int Fan ::getStatusNum() const{
	return this ->statuses.size();
}

string Fan ::to_str() const{
	stringstream ss;
	ss <<"{id=\"" <<this ->id<< "\", " 
		<<"screenName=\"" <<this ->name <<"\", " 
		<<"location=\"" <<this ->city <<"\", " 
		<<"description=\"" <<this ->desc <<"\", " 
		<<"gender=\"" <<this ->gender <<"\", " 
		<<"followersCount=\"" <<this ->followerCnt <<"\", " 
		<<"friendsCount=\"" <<this ->followingCnt <<"\", " 
		<<"statusesCount=\"" <<this ->statusCnt <<"\", " 
		<<"favouritesCount=\"" <<this ->favouritesCnt <<"\", " 
		<<"createdAt=\"" <<this ->createdAt <<"\", " 
		<<"recentlyStatus=" <<(this ->statuses.size() == 0 ? "" : this ->statuses[0].to_str()) <<", " 
		<<"isZombie=\"" <<this ->isZombie <<"\"}";
	return ss.str();
}

void Fan ::addStatus(const Status &s){
	this ->statuses.push_back(s);
}


void Fan ::comFeature(){
	this ->followerRate = (double)(this ->followerCnt)/(this ->followingCnt);
	if (this ->statuses.size() == 0){
		this ->statusStampAvg = 0;
		this ->RWStatusRate = 0.0;
	}
	else {
		initMonthDict();
		vector<int> interval;
		int originalSum = 0;
		for (int i=0; i<this ->statuses.size(); i++){
			if (i != this ->statuses.size()-1){
				int inter = conTime(this ->statuses[i].getCreatedAt())-conTime(this ->statuses[i+1].getCreatedAt());
				interval.push_back(inter);
			}
			if (this ->statuses[i].checkIsOriginal()){
				originalSum ++;
			}
		}
		sort(interval.begin(), interval.end());
		int interSum = 0;
		int interNum = 0;
		for (int i=0; i<3; i++){
			if (i >= interval.size()){
				break;
			}
			interSum += interval[i];
			interNum ++;
		}
		this ->statusStampAvg = (interNum == 0 ? 0 : interSum/interNum);
		this ->RWStatusRate = (double)(this ->statuses.size()-originalSum)/(this ->statuses.size());
	}
}

double Fan ::getFollowerRate() const{
	return this ->followerRate;
}

int Fan ::getStatusStampAvg() const {
	return this ->statusStampAvg;
}

double Fan ::getRWStatusRate() const{
	return this ->RWStatusRate;
}



void Fan ::checkZombie(){
	int ticket = 0;
	if (this ->followerRate < 0.18 && (this ->followerCnt+this ->followingCnt <380 && this ->followerCnt+this ->followingCnt > 250)){
		ticket ++;
	}
	if (this ->statusStampAvg < 10 && this ->statuses.size() > 1){
		ticket ++;
	}
	if (this ->RWStatusRate < 0.25 &&this ->statuses.size() != 0){
		ticket ++;
	}

	if (ticket >= 2){
		this ->isZombie = true;
	}
	else {
		this ->isZombie = false;
	}
}

bool Fan ::getIsZombie() const{
	return this ->isZombie;
}
