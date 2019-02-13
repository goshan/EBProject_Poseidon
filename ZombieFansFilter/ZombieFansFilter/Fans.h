#ifndef FANS_H_
#define FANS_H_


#include <string>
#include <vector>
#include "Status.h"
using namespace std;





class Fan{
private:
	string id;
	string name;
	string city;
	string desc;
	string gender;
	int followerCnt;
	int followingCnt;
	int statusCnt;
	int favouritesCnt;
	string createdAt;
	vector<Status> statuses;

	//feature
	double followerRate;         //follower/following
	int statusStampAvg;    //averrage of minum 3 status stamp time interval
	double RWStatusRate;   //RWStatuses/sum of statuses

	//fans type
	bool isZombie;

public:
	Fan();
	Fan(const string &json);
	Fan(const Fan &f);
	bool operator = (const Fan &f);
	string getId() const;
	string getName() const;
	string getCity() const;
	string getDesc() const;
	string getGender() const;
	int getFollowerCnt() const;
	int getFollowingCnt() const;
	int getStatusCnt() const;
	int getFavouritesCnt() const;
	string getCreatedAt() const;
	int getStatusNum() const;
	string to_str() const;
	void addStatus(const Status &s);

	void comFeature();
	double getFollowerRate() const;
	int getStatusStampAvg() const;
	double getRWStatusRate() const;

	void checkZombie();

	bool getIsZombie() const;
};




#endif