#ifndef STATUS_H_
#define STATUS_H_

#include <string>
using namespace std;





class RWStatus{
private:
	string createdAt;
	string id;
	string content;
	int repostsCnt;
	int commentsCnt;
public:
	RWStatus();
	RWStatus(const string &json);
	RWStatus(const RWStatus &s);
	bool operator = (const RWStatus &s);
	string getCreatedAt() const;
	string getId() const;
	string getContent() const;
	int getRepostsCnt() const;
	int getCommectsCnt() const;
	string to_str() const;
};


class Status{
private:
	string createdAt;
	string id;
	string content;
	int repostsCnt;
	int commentsCnt;

	//origin status
	bool isOriginal;
	RWStatus retweetedStatus;
public:
	Status();
	Status(const string &json);
	Status(const Status &s);
	bool operator = (const Status &s);
	string getCreatedAt() const;
	string getId() const;
	string getContent() const;
	int getRepostsCnt() const;
	int getCommectsCnt() const;
	bool checkIsOriginal() const;
	RWStatus getRetweetedStatus() const;
	string to_str() const;
};


#endif