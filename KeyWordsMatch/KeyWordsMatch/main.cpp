#include <string>
#include <iostream>
#include "FuncTools.h"
using namespace std;



int main(int argc, char *argv[]){
	if (argc < 4){
		cout <<"parameters num error" <<endl;
		return 0;
	}
	string target(argv[1]);
	string resource(argv[2]);
	string res_dir(argv[3]);

	loadHowNet();
	loadTarget(target);
	getSimilarity(resource, res_dir);
	cout <<"process_finished" <<endl;
}