#include <string>
#include <iostream>
#include "FuncTools.h"
using namespace std;



int main(int argc, char *argv[]){
	if (argc < 4){
		cout <<"parameters num error" <<endl;
		return 0;
	}
	string zombieType(argv[1]);
	string fans(argv[2]);
	string res_dir(argv[3]);

	loadZombieType(zombieType);
	loadFans(fans);
	getFinalWords();
	toFile(res_dir);
	cout <<"process_finished" <<endl;
}