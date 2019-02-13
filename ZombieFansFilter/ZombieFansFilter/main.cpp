#include <iostream>
#include <locale>
#include "FuncTools.h"
using namespace std;





int main(int argc, char *argv[]){
	locale ::global(locale(""));
	
	if (argc < 3){
		cout <<"num of parameters error!" <<endl;
		return 0;
	}

	string dir(argv[1]);
	string res_dir(argv[2]);
	
	zombieFansFilter(dir, res_dir);

	return 0;
}