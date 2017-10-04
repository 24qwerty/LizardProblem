public class LunarBot {
	static int cc=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] surface={{-100,1,2,-1,4,1,4,2,-2},
						 {-2,4,1,0,-2,-1,0,1,2},
						 {1,2,-1,1,-100,4,2,-1,1},
						 {0,2,0,-2,2,1,-100,2,-1},
						 {-2,1,4,1,0,-1,4,1,-2},
						 {-1,-100,1,-1,0,1,2,-2,0},
						 {2,4,-2,1,0,2,-1,-100,2},
						 {1,0,2,4,-1,-2,1,0,1},
						 {0,-2,-1,1,-100,1,2,-1,4}};
		boolean[][] visited={{false,false,false,false,false,false,false,false,false},
				                {false,false,false,false,false,false,false,false,false},
				                {false,false,false,false,false,false,false,false,false},
				                {false,false,false,false,false,false,false,false,false},
				                {false,false,false,false,false,false,false,false,false},
				                {false,false,false,false,false,false,false,false,false},
				                {false,false,false,false,false,false,false,false,false},
				                {false,false,false,false,false,false,false,false,false},
				                {false,false,false,false,false,false,false,false,false}};
		System.out.println(traverse(surface, visited, 4,4,false)+"--"+cc+" "+visited[4][4]);
	//	System.out.println(traverse(surface, visited, 2,5,false,4,4)+" "+cc);
	}
	static int traverse(int[][] s,boolean[][] v,int i,int j,boolean b){
		if(i==4 && j==4 && b){
			return 0;
		}
		
		int[] score={-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000};
		for(int k=0;k<16;k++){
			if(isValidMove(i, j, k, s,v)){
				score[k]=count(i,j,k,s,v);
			}
			
		}
		if(score[0]==score[1] && score[0]==score[2] && score[0]==score[3] && score[0]==score[4] && score[0]==score[5] && score[0]==score[6] && score[0]==score[7] && score[0]==score[8] && score[0]==score[9] && score[0]==score[10] && score[0]==score[11] && score[0]==score[12] && score[0]==score[13] && score[0]==score[14] && score[0]==score[15] && score[0]==-1000)
		{
			return -123;
		}
		int k=max(score);
		int l=k;
		int k2=secondLargest(score);
		int finalScore=score[k];
		//System.out.println(k+" "+i+" "+j);
		v[i+getX(k)][j+getY(k)]=true;
		
		cc++;
		//System.out.print(cc+" ");
		printSolution(k);
		//System.out.println();
		int g=traverse(s,v,i+getX(k),j+getY(k),true);
		if(g==-123){
			score[k]=-12345767;
			k=max(score);
			finalScore=score[k];
			v[i+getX(l)][j+getY(l)]=false;
			System.out.println("--");
			printSolution(k);
			g=traverse(s,v,i+getX(k),j+getY(k),true);
		}
		if(k2!=-1){
			
		}
		
		finalScore+=g;
		//System.out.println(finalScore);
		return finalScore;
	}
static int secondLargest(int[] s){
	int max=-10000,ind=-1;
	for(int i=0;i<s.length;i++){
		if(s[i]>=max ){
			max=s[i];
			ind=i;
		}
	}
	int m2=-1000;
	for(int i=0;i<s.length;i++){
		if(s[i]>=m2 && s[i]<max ){
			max=s[i];
			ind=i;
		}
	}
	return ind;
}
	static int getX(int s){
		switch(s){
		case 0:
		case 1:
		case 14:
		case 15:
			return -2;
		case 2:
		case 3:
		case 13:
		case 12:
			return -1;
		case 6:
		case 7:
		case 8:
		case 9:
			return 2;
		case 4:
		case 5:
		case 11:
		case 10:
			return 1;
		}
		return 0;
	}
	static int getY(int s){
		switch(s){
		case 10:
		case 11:
		case 12:
		case 13:
			return -2;
		case 9:
		case 8:
		case 14:
		case 15:
			return -1;
		case 2:
		case 3:
		case 4:
		case 5:
			return 2;
		case 0:
		case 1:
		case 6:
		case 7:
			return 1;
		}
		return 0;
	}
	static int max(int[] s){
		int max=-1000,ind=0;
		for(int i=0;i<s.length;i++){
			if(s[i]>=max ){
				max=s[i];
				ind=i;
			}
		}
		return ind;
	}

	static int count(int i,int j,int step,int[][] s,boolean[][] v){
		int c=0;
		switch(step){
		case 0:
			c+=(s[i-1][j]+s[i-2][j]+ s[i-2][j+1]);
			return c;
		case 1:
			c+=(s[i-1][j+1]+ s[i-2][j+1]+ s[i][j+1]);
			return c;
		case 2:
			c+=(s[i-1][j]+s[i-1][j+1]+ s[i-1][j+2]);
			return c;
		case 3:
			c+=(s[i][j+1]+s[i][j+2]+ s[i-1][j+2]);
			return c;
		case 4:
			c+=(s[i][j+1]+s[i][j+2]+s[i+1][j+2]);
			return c;
		case 5:
			c+=(s[i+1][j]+s[i+1][j+1]+s[i+1][j+2]);
			return c;
		case 6:
			c+=(s[i][j+1]+s[i+1][j+1]+ s[i+2][j+1]);
			return c;
		case 7:
			c+=(s[i+2][j]+ s[i+1][j]+s[i+2][j+1]);
			return c;
		//////////////////////////////////////////////////////////
		case 8:
			c+=(s[i+1][j]+ s[i+2][j]+s[i+2][j-1]);
			return c;
		case 9:
			c+=(s[i][j-1]+s[i+1][j-1]+s[i+2][j-1]);
			return c;
		case 10:
			//System.out.println(s[i][j-2]+" _"+ s[i][j-1]+" _"+s[i+1][j-2]);
			c+=(s[i][j-2]+ s[i][j-1]+s[i+1][j-2]);
			return c;
		case 11:
			c+=(s[i+1][j-2]+s[i+1][j-1]+s[i+1][j]);
			return c;
		case 12:
			c+=(s[i][j-1]+s[i][j-2]+s[i-1][j-2]);
			return c;
		case 13:
			c+=(s[i-1][j]+s[i-1][j-1]+ s[i-1][j-2]);
			return c;
		case 14:
			c+=(s[i][j-1]+s[i-1][j-1]+s[i-2][j-1]);
			return c;
		case 15:
			c+=(s[i-2][j]+ s[i-1][j]+ s[i-2][j-1]);
			return c;
		}
	
		
		return 0;
	}
	static boolean isValidMove(int i,int j,int step,int[][] s,boolean[][] v){
		
			switch(step){
			case 0:
				if(i-2>=0 && i-2<=8 && j+1>=0 && j+1<=8){
					if(s[i-1][j]==-100 || s[i-2][j]==-100 || s[i-2][j+1]==-100){
						return false;
					}
					else{
						if(!v[i-2][j+1]){
							return true;
						}
						else
							return false;
					}
				}
				break;
			case 1:
				if(i-2>=0 && i-2<=8 && j+1>=0 && j+1<=8){
					if(s[i-1][j+1]==-100 || s[i-2][j+1]==-100 || s[i][j+1]==-100){
						return false;
					}
					else{
						if(!v[i-2][j+1]){
							return true;
						}
						else
							return false;
					}
				}
				break;
			case 2:
				if(i-1>=0 && i-1<=8 && j+2>=0 && j+2<=8){//i-2
					if(s[i-1][j]==-100 || s[i-1][j+1]==-100 || s[i-1][j+2]==-100){
						return false;
					}
					else{
						if(!v[i-1][j+2]){
							return true;
						}
						else
							return false;
					}
				}
				break;	
			case 3:
				if(i-1>=0 && i-1<=8 && j+2>=0 && j+2<=8){//
					if(s[i][j+1]==-100 || s[i][j+2]==-100 || s[i-1][j+2]==-100){
						return false;
					}
					else{
						if(!v[i-1][j+2]){//
							return true;
						}
						else
							return false;
					}
				}
				break;
			case 4:
				if(i+1>=0 && i+1<=8 && j+2>=0 && j+2<=8){
					if(s[i][j+1]==-100 || s[i][j+2]==-100 || s[i+1][j+2]==-100){
						return false;
					}
					else{
						if(!v[i+1][j+2]){
							return true;
						}
						else
							return false;
					}
				}
				break;	
			case 5:
				if(i+1>=0 && i+1<=8 && j+2>=0 && j+2<=8){
					if(s[i+1][j]==-100 || s[i+1][j+1]==-100 || s[i+1][j+2]==-100){
						return false;
					}
					else{
						if(!v[i+1][j+2]){
							return true;
						}
						else
							return false;
					}
				}
				break;
			case 6:
				if(i+2>=0 && i+2<=8 && j+1>=0 && j+1<=8){
					if(s[i][j+1]==-100 || s[i+1][j+1]==-100 || s[i+2][j+1]==-100){
						return false;
					}
					else{
						if(!v[i+2][j+1]){
							return true;
						}
						else
							return false;
					}
				}
				break;
			case 7:
				if(i+2>=0 && i+2<=8 && j+1>=0 && j+1<=8){
					if(s[i+2][j]==-100 || s[i+1][j]==-100 || s[i+2][j+1]==-100){
						return false;
					}
					else{
						if(!v[i+2][j+1]){
							return true;
						}
						else
							return false;
					}
				}
				break;
			//////////////////////////////////////////////////////////
			case 8:
				if(i+2>=0 && i+2<=8 && j-1>=0 && j-1<=8){
					if(s[i+1][j]==-100 || s[i+2][j]==-100 || s[i+2][j-1]==-100){
						return false;
					}
					else{
						if(!v[i+2][j-1]){
							return true;
						}
						else
							return false;
					}
				}
				break;
			case 9:
				if(i+2>=0 && i+2<=8 && j-1>=0 && j-1<=8){
					if(s[i][j-1]==-100 || s[i+1][j-1]==-100 || s[i+2][j-1]==-100){
						return false;
					}
					else{
						if(!v[i+2][j-1]){
							return true;
						}
						else
							return false;
					}
				}
				break;
			case 10:
				if(i+1>=0 && i+1<=8 && j-2>=0 && j-2<=8){
					if(s[i][j-2]==-100 || s[i][j-1]==-100 || s[i+1][j-2]==-100){
						return false;
					}
					else{
						if(!v[i+1][j-2]){
							return true;
						}
						else
							return false;
					}
				}
				break;	
			case 11:
				if(i+1>=0 && i+1<=8 && j-2>=0 && j-2<=8){
					if(s[i+1][j-2]==-100 || s[i+1][j-1]==-100 || s[i+1][j]==-100){
						return false;
					}
					else{
						if(!v[i+1][j-2]){
							return true;
						}
						else
							return false;
					}
				}
				break;
			case 12:
				if(i-1>=0 && i-1<=8 && j-2>=0 && j-2<=8){
					if(s[i][j-1]==-100 || s[i][j-2]==-100 || s[i-1][j-2]==-100){
						return false;
					}
					else{
						if(!v[i-1][j-2]){
							return true;
						}
						else
							return false;
					}
				}
				break;	
			case 13:
				if(i-1>=0 && i-1<=8 && j-2>=0 && j-2<=8){
					if(s[i-1][j]==-100 || s[i-1][j-1]==-100 || s[i-1][j-2]==-100){
						return false;
					}
					else{
						if(!v[i-1][j-2]){
							return true;
						}
						else
							return false;
					}
				}
				break;
			case 14:
				if(i-2>=0 && i-2<=8 && j-1>=0 && j-1<=8){
					if(s[i][j-1]==-100 || s[i-1][j-1]==-100 || s[i-2][j-1]==-100){
						return false;
					}
					else{
						if(!v[i-2][j-1]){
							return true;
						}
						else
							return false;
					}
				}
				break;
			case 15:
				if(i-2>=0 && i-2<=8 && j-1>=0 && j-1<=8){
					if(s[i-2][j]==-100 || s[i-1][j]==-100 || s[i-2][j-1]==-100){
						return false;
					}
					else{
						if(!v[i-2][j-1]){
							return true;
						}
						else
							return false;
					}
				}
				break;
			}
		
		return false;
	}
	static void printSolution(int step){
		switch (step) {
		case 0:
			System.out.print(" N,N,E ");
			break;
		case 1:
			System.out.print(" E,N,N ");
			break;
		case 2:
			System.out.print(" N,E,E ");
			break;
		case 3:
			System.out.print(" E,E,N ");
			break;
		case 4:
			System.out.print(" E,E,S ");
			break;
		case 5:
			System.out.print(" S,E,E ");
			break;
		case 6:
			System.out.print(" E,S,S ");
			break;
		case 7:
			System.out.print(" S,S,E ");
			break;
		case 8:
			System.out.print(" S,S,W ");
			break;
		case 9:
			System.out.print(" W,S,S ");
			break;
		case 10:
			System.out.print(" W,W,S ");
			break;
		case 11:
			System.out.print(" S,W,W ");
			break;
		case 12:
			System.out.print(" W,W,N ");
			break;
		case 13:
			System.out.print(" N,W,W ");
			break;
		case 14:
			System.out.print(" W,N,N ");
			break;
		case 15:
			System.out.print(" N,N,W ");
			break;
		}
		System.out.print("|");
	}
}

