#ifndef FUNCTOOLS_H_
#define FUNCTOOLS_H_

#include <string>
using namespace std;



void switchSegResource(bool isOpen);
void switchStopWords(bool isOpen);
void loadHowNet();
double getSimilarity(const string &question, const string &connection, const string &output);



#endif