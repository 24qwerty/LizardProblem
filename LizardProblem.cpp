#include <iostream>
#include <list>
#include <queue>
#include <stack>
#include <vector>
#include <iterator>
#include <ctime>
#include <fstream>
#include <string>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <sys/time.h>
#include <ctime>
#include <random>
using namespace std;
time_t rr1;
class ciz{
public:
    int c,sx,sy,ex,ey;
    ciz(int cc,int sxx,int syy,int exx,int eyy){
        c=cc;
        sx=sxx;
        sy=syy;
        ex=exx;
        ey=eyy;
    }
};
class Coordinate{
public:
    int x;
    int y;
    Coordinate(int a,int b){
        x=a;
        y=b;
    }
};
class State{
public:
    std::vector <Coordinate> queens;
    std::vector <Coordinate> trees;
    int n,m;
    State(std::vector <Coordinate > q,std::vector <Coordinate > t,int d,int mm){
        for (int i=0;i<q.size();i++){
            queens.push_back(q[i]);
        }
        for (int i=0;i<t.size();i++){
            trees.push_back(t[i]);
        }
        n=d;
        m=mm;
    }
    int checkscore2(){
        int total=0;
        std::vector <std::vector<int> > temporary_land(n, std::vector<int>(n, 0));
        for(int i=0;i<trees.size();i++){
            Coordinate c=trees[i];
            if(c.x>=0 && c.y>=0){
                temporary_land[c.x][c.y]=2;
            }
        }
        for(int a=0;a<queens.size();a++){
            for(int b=a+1;b<queens.size() ;b++){
                Coordinate c1=queens[a];
                Coordinate c2=queens[b];
                int attack=false;
                {
                    if(c1.y==c2.y){
                        if(c1.x<c2.x){
                            for(int bv=c1.x+1;bv<=c2.x && temporary_land[bv][c1.y]!=2;bv++){                                if(bv==c2.x){
                                attack=true;
                            }
                            }
                        }
                        else{
                            for(int bv=c1.x-1;bv>=c2.x && temporary_land[bv][c1.y]!=2;bv--){
                                if(bv==c2.x){
                                    attack=true;
                                }
                            }
                        }
                    }
                    else{
                        if(c1.x==c2.x){
                            if(c1.y<c2.y){
                                for(int bv=c1.y+1;bv<=c2.y && temporary_land[c1.x][bv]!=2;bv++){
                                    if(bv==c2.y){
                                        attack=true;
                                    }
                                }
                            }
                            else{
                                int ki=0;
                                for(int bv=c1.y-1;bv>=c2.y && temporary_land[c1.x][bv]!=2;bv--){
                                    ki=ki+1;
                                    if(bv==c2.y){
                                        attack=true;
                                    }
                                }
                            }
                        }
                        else{
                            int xx=c2.x;
                            int yy=c2.y;
                            xx=xx+1;
                            yy=yy+1;
                            while(xx>=0 && xx<n && yy>=0 && yy<n && temporary_land[xx][yy]!=2){
                                if(c1.x==xx && c1.y==yy){
                                    attack=true;
                                    break;
                                }
                                xx=xx+1;
                                yy=yy+1;
                            }
                            xx=c2.x;
                            yy=c2.y;
                            xx=xx-1;
                            yy=yy-1;
                            while(xx>=0 && xx<n && yy>=0 && yy<n && temporary_land[xx][yy]!=2){
                                if(c1.x==xx && c1.y==yy){
                                    attack=true;
                                    break;
                                }
                                xx=xx-1;
                                yy=yy-1;
                            }
                            xx=c2.x;
                            yy=c2.y;
                            xx=xx+1;
                            yy=yy-1;
                            while(xx>=0 && xx<n && yy>=0 && yy<n && temporary_land[xx][yy]!=2){
                                if(c1.x==xx && c1.y==yy){
                                    attack=true;
                                    break;
                                }
                                xx=xx+1;
                                yy=yy-1;
                            }
                            xx=c2.x;
                            yy=c2.y;
                            xx=xx-1;
                            yy=yy+1;
                            while(xx>=0 && xx<n && yy>=0 && yy<n && temporary_land[xx][yy]!=2){
                                if(c1.x==xx && c1.y==yy){
                                    attack=true;
                                    break;
                                }
                                xx=xx-1;
                                yy=yy+1;
                            }
                        }
                    }
                }
                if(attack==true){
                    total=total+1;
                }
            }
        }
        return total;
    }
    State generateNextState(){
        State state(queens,trees,n,m);
        int aa=state.queens.size();
        random_device rd;
        mt19937 gen(rd());
        uniform_real_distribution<> dis(0,aa);
        uniform_real_distribution<> dis2(0,n);
        int k=dis(gen);
        Coordinate cr=state.queens[k];
        int x,y;
        bool j=false;
        while(!j){
            j=false;
            x=dis2(gen);
            y=dis2(gen);
            bool bq=false,bt=false;
            for(int ye=0;ye<state.queens.size();ye++){
                if(ye!=k){
                    Coordinate co=state.queens[ye];
                    if((x==co.x && y==co.y)){
                        bq=true;
                        break;
                    }
                }
            }
            for(int ye=0;ye<state.trees.size();ye++){
                Coordinate co=state.trees[ye];
                if((x==co.x && y==co.y)){
                    bt=true;
                    break;
                }
            }
            if(!bq and !bt){
                Coordinate co(x,y);
                state.queens[k]=co;
                j=true;
            }
            else{
                j=false;
            }
        }
        return state;
    }
    void generateStartState(){
        int x,y;
        random_device rd;
        mt19937 gen(rd());
        uniform_real_distribution<> dis(0,n);
        for(int ii=0;ii<m;ii++){
            bool j=false;
            while(!j){
                x=dis(gen);
                y=dis(gen);
                bool bq=false,bt=false;
                for(int ye=0;ye<queens.size();ye++){
                    Coordinate co=queens[ye];
                    if((x==co.x && y==co.y)){
                        bq=true;
                    }
                }
                for(int ye=0;ye<trees.size();ye++){
                    Coordinate co=trees[ye];
                    if((x==co.x && y==co.y)){
                        bt=true;
                    }
                }
                if(!bq and !bt){
                    Coordinate co(x,y);
                    queens.push_back(co);
                    j=true;
                }
                else{
                    j=false;
                }
            }
        }
    }
};
class Node2{
public:
    std::vector <Coordinate> queens;
    std::vector <Coordinate> trees;
    int depth;
    int n;
    Node2(std::vector <Coordinate > q,std::vector <Coordinate > t,int d,int ny){
        for (int i=0;i<q.size();i++){
            queens.push_back(q[i]);
        }
        for (int i=0;i<t.size();i++){
            trees.push_back(t[i]);
        }
        depth=d;
        n=ny;
    }
    list<Node2> getChildren_dfs(list<ciz> l,int gq){
        int x,xx,y,yy,conflict,i,j,c;
        list<Node2> children;
        c=depth;
        if(c<l.size()){
            std::vector <std::vector<int> > temporary_land(n, std::vector<int>(n, 0));
            list<ciz>::iterator it = l.begin();
            advance(it, c);
            ciz columnlist=*it;
            x=columnlist.sx;
            xx=columnlist.ex;
            y=columnlist.sy;
            yy=columnlist.ey;
            for(int i=0;i<queens.size();i++){
                Coordinate c=queens[i];
                temporary_land[c.x][c.y]=1;
            }
            for(int i=0;i<trees.size();i++){
                Coordinate c=trees[i];
                temporary_land[c.x][c.y]=2;
            }
            int f=queens.size();
            if((l.size()-(c))>(gq-queens.size())){
                Node2 a(queens,trees,(c+1),n);
                children.push_back(a);
            }
            for(int xy=xx;xy>=x;xy--){
                if(temporary_land[xy][y]==0){
                    conflict=0;
                    for(int k=y-1;k>=0;k--){
                        if(temporary_land[xy][k]==2){
                            break;
                        }
                        if(temporary_land[xy][k]==1){
                            conflict=conflict+1;
                            break;
                        }
                    }
                    for(int k=y+1;k<n;k++){
                        if(temporary_land[xy][k]==2){
                            break;
                        }
                        if(temporary_land[xy][k]==1){
                            conflict=conflict+1;
                            break;
                        }
                    }
                    i=xy-1;
                    j=y-1;
                    while( i>=0 and i<n and j>=0 and j<n){
                        if(temporary_land[i][j]==2){
                            break;
                        }
                        if(temporary_land[i][j]==1){
                            conflict=conflict+1;
                            break;
                        }
                        i=i-1;
                        j=j-1;
                    }
                    i=xy-1;
                    j=y+1;
                    while( i>=0 and i<n and j>=0 and j<n){
                        if(temporary_land[i][j]==2){
                            break;
                        }
                        if(temporary_land[i][j]==1){
                            conflict=conflict+1;
                            break;
                        }
                        i=i-1;
                        j=j+1;
                    }
                    i=xy+1;
                    j=y+1;
                    while( i>=0 and i<n and j>=0 and j<n){
                        if(temporary_land[i][j]==2){
                            break;
                        }
                        if(temporary_land[i][j]==1){
                            conflict=conflict+1;
                            break;
                        }
                        i=i+1;
                        j=j+1;
                    }
                    i=xy+1;
                    j=y-1;
                    while( i>=0 and i<n and j>=0 and j<n){
                        if(temporary_land[i][j]==2){
                            break;
                        }
                        if(temporary_land[i][j]==1){
                            conflict=conflict+1;
                            break;
                        }
                        i=i+1;
                        j=j-1;
                    }
                    if(conflict==0){
                        std::vector <Coordinate > q;
                        for (int i=0;i<queens.size();i++){
                            q.push_back(queens[i]);
                        }
                        Coordinate cc(xy,y);
                        q.push_back(cc);
                        Node2 ch(q,trees,(c+1),n);
                        children.push_back(ch);
                    }
                }
            }
        }
        return children;
    }
    list<Node2> getChildren(list<ciz> l,int gq){
        int x,xx,y,yy,conflict,i,j,c;
        list<Node2> children;
        c=depth;
        if(c<l.size()){
            std::vector <std::vector<int> > temporary_land(n, std::vector<int>(n, 0));
            list<ciz>::iterator it = l.begin();
            advance(it, c);
            ciz columnlist=*it;
            x=columnlist.sx;
            xx=columnlist.ex;
            y=columnlist.sy;
            yy=columnlist.ey;
            for(int i=0;i<queens.size();i++){
                Coordinate c=queens[i];
                temporary_land[c.x][c.y]=1;
            }
            for(int i=0;i<trees.size();i++){
                Coordinate c=trees[i];
                temporary_land[c.x][c.y]=2;
            }
            int f=queens.size();
            if((l.size()-(c))>=(gq-f)){
                for(int xy=xx;xy>=x;xy--){
                    if(temporary_land[xy][y]==0){
                        conflict=0;
                        for(int k=y-1;k>=0;k--){
                            if(temporary_land[xy][k]==2){
                                break;
                            }
                            if(temporary_land[xy][k]==1){
                                conflict=conflict+1;
                                break;
                            }
                        }
                        for(int k=y+1;k<n;k++){
                            if(temporary_land[xy][k]==2){
                                break;
                            }
                            if(temporary_land[xy][k]==1){
                                conflict=conflict+1;
                                break;
                            }
                        }
                        i=xy-1;
                        j=y-1;
                        while( i>=0 and i<n and j>=0 and j<n){
                            if(temporary_land[i][j]==2){
                                break;
                            }
                            if(temporary_land[i][j]==1){
                                conflict=conflict+1;
                                break;
                            }
                            i=i-1;
                            j=j-1;
                        }
                        i=xy-1;
                        j=y+1;
                        while( i>=0 and i<n and j>=0 and j<n){
                            if(temporary_land[i][j]==2){
                                break;
                            }
                            if(temporary_land[i][j]==1){
                                conflict=conflict+1;
                                break;
                            }
                            i=i-1;
                            j=j+1;
                        }
                        i=xy+1;
                        j=y+1;
                        while( i>=0 and i<n and j>=0 and j<n){
                            if(temporary_land[i][j]==2){
                                break;
                            }
                            if(temporary_land[i][j]==1){
                                conflict=conflict+1;
                                break;
                            }
                            i=i+1;
                            j=j+1;
                        }
                        i=xy+1;
                        j=y-1;
                        while( i>=0 and i<n and j>=0 and j<n){
                            if(temporary_land[i][j]==2){
                                break;
                            }
                            if(temporary_land[i][j]==1){
                                conflict=conflict+1;
                                break;
                            }
                            i=i+1;
                            j=j-1;
                        }
                        if(conflict==0){
                            std::vector <Coordinate > q;
                            for (int i=0;i<queens.size();i++){
                                q.push_back(queens[i]);
                            }
                            Coordinate cc(xy,y);
                            q.push_back(cc);
                            Node2 ch(q,trees,(c+1),n);
                            children.push_back(ch);
                        }
                    }
                }
                if((l.size()-(c))>(gq-f)){
                    Node2 a(queens,trees,(c+1),n);
                    children.push_back(a);
                }
            }
        }
        return children;
    }
};
bool goalTest2(Node2 n,int g){
    int count=0;
    int nland=n.n;
    if(n.queens.size()==g){
        return true;
    }
    return false;
}

Node2 bfs2(Node2 root1,list<ciz> l,int gland){
    int nland=root1.n;
    std::vector <Coordinate> vll;
    Coordinate cc(-1,-1);
    vll.push_back(cc);
    bool r;
    queue<Node2> q;
    if(l.size()<gland || l.size()<=0){
        Node2 aa(vll,vll,0,nland);
        return aa;
    }
    q.push(root1);
    while(q.size()>0){
        Node2 node=q.front();
        q.pop();
        list<Node2> children;
        children=node.getChildren(l,gland);
        list <Node2> :: iterator it;
        for(it = children.begin(); it != children.end(); ++it)
        {
            Node2 ch=*it;
            r=goalTest2(ch,gland);
            if(r==true){
                return ch;
            }
            q.push(ch);
        }
    }
    Node2 aa(vll,vll,0,nland);
    return aa;
}
Node2 dfs2(Node2 root1,list<ciz> l,int gland){
    int nland=root1.n;
    std::vector <Coordinate> vll;
    Coordinate cc(-1,-1);
    vll.push_back(cc);
    bool r;
    stack<Node2> q;
    if(l.size()<gland || l.size()<=0 ){
        Node2 aa(vll,vll,0,nland);
        return aa;
    }
    q.push(root1);
    while(q.size()>0){
        Node2 node=q.top();
        q.pop();
        r=goalTest2(node,gland);
        if(r==true){
            return node;
        }
        list<Node2> children;
        children=node.getChildren_dfs(l,gland);
        list <Node2> :: iterator it;
        for(it = children.begin(); it != children.end(); ++it)
        {
            Node2 ch=*it;
            q.push(ch);
        }
    }
    Node2 aa(vll,vll,0,nland);
    return aa;
}
bool canBeTaken(double xxx){
    random_device rd;
    mt19937 gen(rd());
    uniform_real_distribution<> dis(0,1);
    double yyy=dis(gen);
    if(yyy<=xxx)
    return true;
    return false;
}
int main(){
    int kr=8;
    double jk=0;
    time_t result1 = time(nullptr);
    time_t result21;
    rr1=result1+290;
    time_t rr=result1+290;
    int c=0;
    list<ciz> l;
    int nnn=5;
    int ggg=0;
    string algorithm;
    string line;
    ifstream inFile;
    inFile.open("input.txt");
    inFile>>algorithm;
    inFile>>nnn;
    std::vector <std::vector<int> > vland(nnn, std::vector<int>(nnn, 0));
    std::vector <Coordinate > qll;
    std::vector <Coordinate > tt;
    inFile>>ggg;
    for(int i=0;i<nnn;i++){
        inFile>>line;
        for (int j = 0; j < nnn; j++) {
            vland[i][j]=(int)line[j]-48;
            if(vland[i][j]==2){
                Coordinate c(i,j);
                tt.push_back(c);
            }
        }
    }
    inFile.close();
    Node2 root(qll,tt,0,nnn);
    for(int i=0;i<nnn;i++){
        int j=0;
        while(j<nnn){
            if(vland[j][i]!=2){
                ciz d(c,j,i,0,0);
                c=c+1;
                while(j<nnn and vland[j][i]!=2){
                    j=j+1;
                }
                d.ex=j-1;
                d.ey=i;
                l.push_back(d);
            }
            j=j+1;
        }
    }
    ofstream file_output;
    if(algorithm=="BFS"){
        Node2 answer=bfs2(root,l,ggg);
        file_output.open("output.txt");
        if(answer.queens.size()==1 && answer.queens[0].x==-1){
            file_output<<"FAIL\n";
            file_output.close();
        }else{
            file_output<<"OK\n";
            std::vector <std::vector<int> > temporary_land(nnn, std::vector<int>(nnn, 0));
            for(int i=0;i<answer.queens.size();i++){
                Coordinate c=answer.queens[i];
                if(c.x>=0 && c.y>=0){
                    temporary_land[c.x][c.y]=1;
                }
            }
            for(int i=0;i<answer.trees.size();i++){
                Coordinate c=answer.trees[i];
                if(c.x>=0 && c.y>=0){
                    temporary_land[c.x][c.y]=2;
                }
            }
            for (int i=0;i<nnn;i++){
                for(int j=0;j<nnn;j++){
                    file_output<<temporary_land[i][j];
                }
                file_output<<"\n";
            }
            file_output.close();
        }
    }
    else if(algorithm=="DFS"){
        Node2 answer=dfs2(root,l,ggg);
        file_output.open("output.txt");
        if(answer.queens.size()==1 && answer.queens[0].x==-1){
            file_output<<"FAIL\n";
            file_output.close();
        }else{
            file_output<<"OK\n";
            std::vector <std::vector<int> > temporary_land(nnn, std::vector<int>(nnn, 0));
            for(int i=0;i<answer.queens.size();i++){
                Coordinate c=answer.queens[i];
                if(c.x>=0 && c.y>=0){
                    temporary_land[c.x][c.y]=1;
                }
            }
            for(int i=0;i<answer.trees.size();i++){
                Coordinate c=answer.trees[i];
                if(c.x>=0 && c.y>=0){
                    temporary_land[c.x][c.y]=2;
                }
            }
            for (int i=0;i<nnn;i++){
                for(int j=0;j<nnn;j++){
                    file_output<<temporary_land[i][j];
                }
                file_output<<"\n";
            }
            file_output.close();
        }
    }
    else{
        file_output.open("output.txt");
        if(l.size()<=0 || l.size()<ggg){
            file_output<<"FAIL\n";
            file_output.close();
        }
        else{
            State start(qll,tt,nnn,ggg);
            start.generateStartState();
            int ch=start.checkscore2();
            int v=0;
            double T=10000000;
            int de;
            State newState(qll,tt,nnn,ggg);
            double r;
            while(ch!=0){
                T=1/(log(jk+1));
                jk=jk+0.8;
                if(time(nullptr)>=rr){
                    file_output<<"FAIL\n";
                    break;
                }
                else{
                    int scs=start.checkscore2();
                    if(scs==0){
                        break;
                    }
                    newState=start.generateNextState();
                    de=newState.checkscore2()-scs;
                    if(de<0){
                        start=newState;
                    }
                    else{
                        r=1/exp((de/T));
                        if(canBeTaken(r)){
                            start=newState;
                        }
                    }
                }
            }
            file_output<<"OK\n";
            std::vector <std::vector<int> > temporary_land(nnn, std::vector<int>(nnn, 0));
            for(int i=0;i<start.queens.size();i++){
                Coordinate c=start.queens[i];
                if(c.x>=0 && c.y>=0){
                    temporary_land[c.x][c.y]=1;
                }
            }
            for(int i=0;i<start.trees.size();i++){
                Coordinate c=start.trees[i];
                if(c.x>=0 && c.y>=0){
                    temporary_land[c.x][c.y]=2;
                }
            }
            for (int i=0;i<nnn;i++){
                for(int j=0;j<nnn;j++){
                    file_output<<temporary_land[i][j];
                }
                file_output<<"\n";
            }
            file_output.close();
        }
    }
    return 0;
}
