#include "Status.h"
#include <sstream>
#include <string>
using namespace std;







RWStatus ::RWStatus(){
	this ->createdAt = "";
	this ->id = "";
	this ->content = "";
	this ->repostsCnt = 0;
	this ->commentsCnt = 0;
}

RWStatus ::RWStatus(const string &json){
	int start = 0;
	int stack = 0;
	string key = "";
	string value = "";
	bool save = false;
	bool txt = true;
	for (int i=0; i<json.length(); i++){
		if (json[i] == '{'){
			stack ++;
			if (stack == 1){
				start = i+1;
			}
		}
		else if (json[i] == '}'){
			stack --;
			if (stack == 0){
				save = true;
				value = json.substr(start, i-start);
			}
		}
		else if (json[i] == '='){
			if (stack == 1){
				key = json.substr(start, i-start);
				start = i+1;
			}
		}
		else if (json[i] == '\"'){
			txt = txt ? false : true;
		}
		else if (json[i] == ','){
			if (stack == 1 && txt){
				save = true;
				value = json.substr(start, i-start);
				start = i+2;
			}
		}

		if (save){
			if (key == "createdAt"){
				this ->createdAt = value.substr(1, value.length()-2);
			}
			else if (key == "id"){
				this ->id = value.substr(1, value.length()-2);
			}
			else if (key == "text"){
				this ->content = value.substr(1, value.length()-2);
			}
			else if (key == "repostsCount"){
				this ->repostsCnt = atoi(value.substr(1, value.length()-2).c_str());
			}
			else if (key == "commentsCount"){
				this ->commentsCnt = atoi(value.substr(1, value.length()-2).c_str());
			}
			save = false;
		}
	}
}

RWStatus ::RWStatus(const RWStatus &s){
	this ->createdAt = s.createdAt;
	this ->id = s.id;
	this ->content = s.content;
	this ->repostsCnt = s.repostsCnt;
	this ->commentsCnt = s.commentsCnt;
}

bool RWStatus ::operator = (const RWStatus &s){
	this ->createdAt = s.createdAt;
	this ->id = s.id;
	this ->content = s.content;
	this ->repostsCnt = s.repostsCnt;
	this ->commentsCnt = s.commentsCnt;
	return true;
}

string RWStatus ::getCreatedAt() const{
	return this ->createdAt;
}

string RWStatus ::getId() const{
	return this ->id;
}

string RWStatus ::getContent() const{
	return this ->content;
}

int RWStatus ::getRepostsCnt() const{
	return this ->repostsCnt;
}

int RWStatus ::getCommectsCnt() const{
	return this ->commentsCnt;
}

string RWStatus ::to_str() const{
	stringstream ss;
	ss <<"{createdAt=\"" <<this ->createdAt <<"\", " 
		<<"id=\"" <<this ->id <<"\", " 
		<<"text=\"" <<this ->content <<"\", " 
		<<"repostsCount=\"" <<this ->repostsCnt <<"\", " 
		<<"commentsCount=\"" <<this ->commentsCnt <<"}";
	return ss.str();
}




Status ::Status(){
	this ->createdAt = "";
	this ->id = "";
	this ->content = "";
	this ->repostsCnt = 0;
	this ->commentsCnt = 0;
	this ->isOriginal = true;
}

Status ::Status(const string &json){
	int start = 0;
	int stack = 0;
	string key = "";
	string value = "";
	bool save = false;
	bool txt = true;
	for (int i=0; i<json.length(); i++){
		if (json[i] == '{'){
			stack ++;
			if (stack == 1){
				start = i+1;
			}
		}
		else if (json[i] == '}'){
			stack --;
			if (stack == 0){
				save = true;
				value = json.substr(start, i-start);
			}
		}
		else if (json[i] == '='){
			if (stack == 1){
				key = json.substr(start, i-start);
				start = i+1;
			}
		}
		else if (json[i] == '\"'){
			if (stack == 1){
				txt = txt ? false : true;
			}
		}
		else if (json[i] == ','){
			if (stack == 1 && txt){
				save = true;
				value = json.substr(start, i-start);
				start = i+2;
			}
		}

		if (save){
			if (key == "createdAt"){
				this ->createdAt = value.substr(1, value.length()-2);
			}
			else if (key == "id"){
				this ->id = value.substr(1, value.length()-2);
			}
			else if (key == "text"){
				this ->content = value.substr(1, value.length()-2);
			}
			else if (key == "repostsCount"){
				this ->repostsCnt = atoi(value.substr(1, value.length()-2).c_str());
			}
			else if (key == "commentsCount"){
				this ->commentsCnt = atoi(value.substr(1, value.length()-2).c_str());
			}
			else if (key == "retweetedStatus"){
				if (value.empty()){
					this ->isOriginal = true;
				}
				else {
					this ->isOriginal = false;
					this ->retweetedStatus = RWStatus(value);
				}
			}
			save = false;
		}
	}
}

Status ::Status(const Status &s){
	this ->createdAt = s.createdAt;
	this ->id = s.id;
	this ->content = s.content;
	this ->repostsCnt = s.repostsCnt;
	this ->commentsCnt = s.commentsCnt;
	this ->isOriginal = s.isOriginal;
	this ->retweetedStatus = s.retweetedStatus;
}

bool Status ::operator = (const Status &s){
	this ->createdAt = s.createdAt;
	this ->id = s.id;
	this ->content = s.content;
	this ->repostsCnt = s.repostsCnt;
	this ->commentsCnt = s.commentsCnt;
	this ->isOriginal = s.isOriginal;
	this ->retweetedStatus = s.retweetedStatus;
	return true;
}

string Status ::getCreatedAt() const{
	return this ->createdAt;
}

string Status ::getId() const{
	return this ->id;
}

string Status ::getContent() const{
	return this ->content;
}

int Status ::getRepostsCnt() const{
	return this ->repostsCnt;
}

int Status ::getCommectsCnt() const{
	return this ->commentsCnt;
}

bool Status ::checkIsOriginal() const{
	return this ->isOriginal;
}

RWStatus Status ::getRetweetedStatus() const{
	return this ->retweetedStatus;
}

string Status ::to_str() const{
	stringstream ss;
	ss <<"{createdAt=\"" <<this ->createdAt <<"\", " 
		<<"id=\"" <<this ->id <<"\", " 
		<<"text=\"" <<this ->content <<"\", " 
		<<"retweetedStatus=" <<(this ->isOriginal ? "" : this ->retweetedStatus.to_str()) <<", " 
		<<"repostsCount=\"" <<this ->repostsCnt <<"\", " 
		<<"commentsCount=\"" <<this ->commentsCnt <<"}";
	return ss.str();
}
