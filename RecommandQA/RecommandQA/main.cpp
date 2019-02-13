#include <string>
#include <iostream>
#include "FuncTools.h"
using namespace std;



int main(int argc, char *argv[]){
	
	if (argc < 4){
		cout <<"parameters num error" <<endl;
		return 0;
	}
	string question(argv[1]);
	string connection(argv[2]);
	string res(argv[3]);
	res += "\\Similairty.res";

	switchSegResource(true);
	switchStopWords(true);
	loadHowNet();
	getSimilarity(question, connection, res);
	switchSegResource(false);
	switchStopWords(false);
	cout <<"process_finished" <<endl;
}