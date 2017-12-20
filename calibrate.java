import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class calibrate {
	static int co = 0;
	static int n;
	static int m;
	static double time;
	static long plus = Long.MAX_VALUE;
	static long minus = Long.MIN_VALUE;
	static int vr = 0;
	static long startTime;
	static long childCount=0;
	public static void main(String[] args) {
		FileReader fr;
		MyState initial_state = null;
			int a25[][] = { { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,2 },
					{ 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,1 },
					{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,2 },
					{ 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,1 },
					{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,2 },
					{ 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,1 },
					{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,2 },
					{ 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,1 },
					{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,2 },
					{ 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,1 },
					{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,2 },
					{ 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,1 },
					{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,2 },
					{ 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,1 },
					{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,2 },
					{ 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 ,1},
					{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,2 },
					{ 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,1 },
					{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,2 },
					{ 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,1 },
					{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,2 },
					{ 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,1 },
					{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,2 },
					{ 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,1 },
					{ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1 ,2},
					{ 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,1 }};
			FileWriter fileWriter;
			try {
				fileWriter = new FileWriter("calibration.txt");
				PrintWriter printWriter = new PrintWriter(fileWriter);
				for (int i = 0; i < 26; i++) {
					for (int j = 0; j < 26; j++) {
						printWriter.print(a25[i][j]);
					}
					printWriter.print("\n");
				}
				printWriter.close();
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long startTime;
			long endTime;
     long endTime2;
			for (int i = 1; i <= 26; i++) {
				n=i;
				initial_state=new MyState(i);
				childCount=0;
				try {
					fr = new FileReader("calibration.txt");
					BufferedReader br = new BufferedReader(fr);
					String a;
					for (int i2 = 0; i2 < i; i2++) {
						a = br.readLine();
						for (int j = 0; j < i; j++) {
							int aa=a.charAt(j);
							initial_state.state[i2][j] = a.charAt(j);
						}
					}
                 br.close();
					startTime=System.nanoTime();
					alphaBetaPruning(new Action(0, 0, initial_state, 0), 1);
					endTime=System.nanoTime();
					long one=((endTime-startTime)/childCount);
					childCount=0;
					alphaBetaPruning(new Action(0, 0, initial_state, 0), 2);
					endTime2=System.nanoTime();
					long two=((endTime2-endTime)/childCount);
					fileWriter = new FileWriter("calibration.txt",true);
					BufferedWriter bw=new BufferedWriter(fileWriter);
					bw.write(((long)(one+two)/(long)2)+"\n");
					bw.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException ee) {
				}
			}
			
	}

	static Action alphaBetaPruning(Action act, int depth) { 
		Action action = maxValue2(act, minus, plus, depth);
		return action;
	}
	static List<Action> generateNewStates(Action mstate, boolean b, float ii) {
		char a[][] = new char[n][n];
		Stack<Position> st = new Stack();
		List<Action> at = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (((int) a[i][j]) == 0 && mstate.state.state[i][j] != '*') {
					MyState ms = new MyState(mstate.state, n);
					st.push(new Position(i, j));
					a[i][j] = 1;
					int c = 0;
					while (!st.isEmpty()) {
						Position rc = st.pop();
						c++;
						ms.state[rc.r][rc.c] = '*';
						if (rc.c + 1 >= 0 && rc.c + 1 < n) {
							if (mstate.state.state[rc.r][rc.c + 1] == mstate.state.state[i][j]
									&& a[rc.r][rc.c + 1] != 1) {
								st.push(new Position(rc.r, rc.c + 1));
								a[rc.r][rc.c + 1] = 1;
							}
						}
						if (rc.c - 1 >= 0 && rc.c - 1 < n) {
							if (mstate.state.state[rc.r][rc.c - 1] == mstate.state.state[i][j]
									&& a[rc.r][rc.c - 1] != 1) {
								st.push(new Position(rc.r, rc.c - 1));
								a[rc.r][rc.c - 1] = 1;
							}
						}
						if (rc.r + 1 >= 0 && rc.r + 1 < n) {
							if (mstate.state.state[rc.r + 1][rc.c] == mstate.state.state[i][j]
									&& a[rc.r + 1][rc.c] != 1) {
								st.push(new Position(rc.r + 1, rc.c));
								a[rc.r + 1][rc.c] = 1;
							}
						}
						if (rc.r - 1 >= 0 && rc.r - 1 < n) {
							if (mstate.state.state[rc.r - 1][rc.c] == mstate.state.state[i][j]
									&& a[rc.r - 1][rc.c] != 1) {
								st.push(new Position(rc.r - 1, rc.c));
								a[rc.r - 1][rc.c] = 1;
							}
						}
					}
					// apply gravity
					char star = '*';
					for (int k = 0; k < n; k++) {
						int uu = -1;
						for (int u = n - 1; u >= 0; u--) {
							if (ms.state[u][k] == star) {
								uu = u;
								break;
							}
						}
						if (uu != -1) {
							for (int u = n - 1; u >= 0; u--) {
								if (ms.state[u][k] != star && uu > u) {
									ms.state[uu][k] = ms.state[u][k];
									ms.state[u][k] = '*';
									for (int u1 = n - 1; u1 >= 0; u1--) {
										if (ms.state[u1][k] == star) {
											uu = u1;
											break;
										}
									}
								}
							}
						}
					}
					int score = mstate.utility;
					if (b) {
						score = score + (c * c);
					} else {
						score = score - (c * c);
					}
					at.add(new Action(i, j, ms, score, mstate, (mstate.depth + 1)));
					childCount++;
				}
			}
		}
		if (b) {
			Collections.sort(at, new Comparator<Action>() {
				@Override
				public int compare(Action o1, Action o2) {
					// TODO Auto-generated method stub
					return (o1.utility - o2.utility);
				}

			});
		} else {
			Collections.sort(at, new Comparator<Action>() {
				@Override
				public int compare(Action o1, Action o2) {
					// TODO Auto-generated method stub
					return (o2.utility - o1.utility);
				}

			});
		}
		return at;
	}

	static Action maxValue2(Action act, long alpha, long beta, int depth) {
		if (terminalState(act.state) || act.depth >= depth) {// || act.depth>3
															// //|| (
															// System.nanoTime()-startTime)/1000000>200
			return act;
		}
		long v = minus;
		Action returningAction = null;
		List<Action> at = generateNewStates(act, true, 1.0f);// (act.state,act.utility,true);
		for (Action aw : at) {
			Action ra = minValue2(aw, alpha, beta, depth);
			if (ra == null) {
				System.out.println("null");
			}
			if (v < ra.utility) {
				v = ra.utility;
				returningAction = ra;
			}
		}
		return returningAction;
	}

	static Action minValue2(Action act, long alpha, long beta, int depth) {
		if (terminalState(act.state) || act.depth >= depth) {
			return act;
		}
		long v = plus;
		Action returningAction = null;
		List<Action> at = generateNewStates(act, false, 1.0f);// (act.state,act.utility,false);
		for (Action aw : at) {
			Action ra = maxValue2(aw, alpha, beta, depth);
			if (ra == null) {
				System.out.println("null here also.");
			}
			if (v > ra.utility) {
				v = ra.utility;
				returningAction = ra;
			}
		}
		if (returningAction == null) {
			System.out.println("2 null here also." + v + " " + alpha + " ");
		}
		return returningAction;
	}

	static boolean terminalState(MyState mstate) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (mstate.state[i][j] != '*') {
					return false;
				}
			}
		}
		return true;
	}
}

class MyState {
	char state[][];

	MyState(int size) {
		state = new char[size][size];
	}

	MyState(MyState ms, int size) {
		state = new char[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				state[i][j] = ms.state[i][j];
			}
		}
	}
}

class Action implements Comparator<Action> {
	int r, c;
	MyState state;
	int utility;
	Action t;
	int depth;

	Action(int r, int c, MyState state, int utility) {
		this.r = r;
		this.c = c;
		this.state = state;
		this.utility = utility;
		depth = 0;
	}

	Action(int r, int c, MyState state, int utility, Action t, int depth) {
		this.r = r;
		this.c = c;
		this.state = state;
		this.utility = utility;
		this.t = t;
		this.depth = depth;
	}

	@Override
	public int compare(Action o1, Action o2) {
		// TODO Auto-generated method stub
		return (o1.utility - o2.utility);
	}
}

class Position {
	int r, c;

	Position(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
