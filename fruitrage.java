import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class fruitrage {
	static int n;
	static int m;
	static double time;
	static long plus = Long.MAX_VALUE;
	static long minus = Long.MIN_VALUE;
	static int vr = 0;
	static long startTime;
	static long averageTime;
	static int blocks;
	static long totalRemainingTime;

	public static void main(String[] args) {
		FileReader fr;
		MyState initial_state = null;
		try {
			startTime = System.nanoTime();
			fr = new FileReader("input.txt");
			BufferedReader br = new BufferedReader(fr);
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			time = Double.parseDouble(br.readLine());
			initial_state = new MyState(n);
			String a;
			for (int i = 0; i < n; i++) {
				a = br.readLine();
				for (int j = 0; j < n; j++) {
					initial_state.state[i][j] = a.charAt(j);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ee) {

		}
     try {
			fr = new FileReader("calibration.txt");
			BufferedReader br = new BufferedReader(fr);
			for (int u = 0; u < n + 25; u++) {
				br.readLine();
			}
			averageTime = Long.parseLong(br.readLine());
         	//System.out.println(averageTime);
			br.close();
		} catch (IOException ee) {

		}
		blocks = blocks(new Action(0, 0, initial_state, 0));
     	Action je;
		if(blocks!=1){
		long timeLeftNow = System.nanoTime() - startTime;
		long givenTime = (long) (time * 1000000000);
		totalRemainingTime = givenTime - timeLeftNow;
		int gg = limitDepth(blocks, averageTime, totalRemainingTime);
		if (gg == 0) {
			je = returnAnyState(new Action(0, 0, initial_state, 0));
		} else {
			totalRemainingTime = totalRemainingTime - (System.nanoTime() - startTime);
			//System.out.println("blocks=" + blocks + "\ngg=" + gg+" n="+n+" time="+time);
			Action aa = alphaBetaPruning(new Action(0, 0, initial_state, 0), gg);
			// System.out.println(aa.utility);
			je = aa;
			// String aaa = "m";
			while (aa.t != null) {
				je = aa;
				aa = aa.t;
			}
		}
		}
		else{
			je = returnAnyState(new Action(0, 0, initial_state, 0));
		}
		char column = (char) (je.c + 65);
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("output.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print((column) + "" + (je.r + 1) + "\n");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					printWriter.print(je.state.state[i][j]);
				}
				printWriter.print("\n");
			}
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 private static MyState applyGravity(MyState ms){
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
				for (int u = uu; u >= 0; u--) {
					if (ms.state[u][k] != star && uu > u) {
						ms.state[uu][k] = ms.state[u][k];
						ms.state[u][k] = '*';
						for (int u1 = uu; u1 >= 0; u1--) {
							if (ms.state[u1][k] == star) {
								uu = u1;
								break;
							}
						}
					}
				}
			}
		}
		return ms;
	}
	private static Action returnAnyState(Action mstate) {
		Stack<Position> st = new Stack();
		MyState ms = null;
		char a[][] = new char[n][n];
		boolean nn = false;
		int c = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (((int) a[i][j]) == 0 && mstate.state.state[i][j] != '*') {
					ms = new MyState(mstate.state, n);
					st.push(new Position(i, j));
					a[i][j] = 1;
					while (!st.isEmpty()) {
						Position rc = st.pop();
						ms.state[rc.r][rc.c] = '*';
						c++;
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
					return new Action(i, j, applyGravity(ms), c * c);
				}
			}
		}
		return null;
	}
	private static int evaluateFunction(Action mstate, boolean isMax) {
		char a[][] = new char[n][n];
		List<Integer> scores = new ArrayList<>();
		Stack<Position> st = new Stack();
		int yu = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (((int) a[i][j]) == 0 && mstate.state.state[i][j] != '*') {
					yu = 1;
					st.push(new Position(i, j));
					a[i][j] = 1;
					while (!st.isEmpty()) {
						Position rc = st.pop();
						if (rc.c + 1 >= 0 && rc.c + 1 < n) {
							if (mstate.state.state[rc.r][rc.c + 1] == mstate.state.state[i][j]
									&& a[rc.r][rc.c + 1] != 1) {
								st.push(new Position(rc.r, rc.c + 1));
								yu++;
								a[rc.r][rc.c + 1] = 1;
							}
						}
						if (rc.c - 1 >= 0 && rc.c - 1 < n) {
							if (mstate.state.state[rc.r][rc.c - 1] == mstate.state.state[i][j]
									&& a[rc.r][rc.c - 1] != 1) {
								st.push(new Position(rc.r, rc.c - 1));
								yu++;
								a[rc.r][rc.c - 1] = 1;
							}
						}
						if (rc.r + 1 >= 0 && rc.r + 1 < n) {
							if (mstate.state.state[rc.r + 1][rc.c] == mstate.state.state[i][j]
									&& a[rc.r + 1][rc.c] != 1) {
								st.push(new Position(rc.r + 1, rc.c));
								yu++;
								a[rc.r + 1][rc.c] = 1;
							}
						}
						if (rc.r - 1 >= 0 && rc.r - 1 < n) {
							if (mstate.state.state[rc.r - 1][rc.c] == mstate.state.state[i][j]
									&& a[rc.r - 1][rc.c] != 1) {
								st.push(new Position(rc.r - 1, rc.c));
								yu++;
								a[rc.r - 1][rc.c] = 1;
							}
						}
					}
					scores.add(yu * yu);
				}
			}
		}
		Collections.sort(scores, Collections.reverseOrder());
		int jk = 0;
		int gg = 0;
		for (Integer ii : scores) {
			if (gg == 0) {
				jk += ii;
				gg = 1;
			} else {
				jk -= ii;
				gg = 0;
			}
		}
		if (!isMax) {
			jk = 0 - jk;
		}
		return jk;
	}

	private static int limitDepth(int blocks, long time, long remainingTime) {
		// TODO Auto-generated method stub
		long rt = (long) ((long) remainingTime*(long)1.8 / (long) blocks);
		// System.out.println(rt);
     	if(blocks!=1){
         	rt = (long) (rt * (long)150 / 100);
        }
		int j = blocks;
		long sum = 1;
		for (int i = 1;; i++) {
			sum += Math.pow(j, i);
			if (i >( blocks * n))
				return i;
			if (sum * time >= rt)
            {
             if((sum*time)<=(remainingTime/(long)3)){
					return i;
				}
				else{
					return i-1;
                }
            }
		}
	}

	static Action alphaBetaPruning(Action act, int depth) { // act with
															// utility=0
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
					int score = mstate.utility;
					if (b) {
						score = score + (c * c);
					} else {
						score = score - (c * c);
					}
					at.add(new Action(i, j, applyGravity(ms), score, mstate, mstate.depth + 1));
					//r++;
				}
			}
		}
		if (!b) {
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
	static int blocks(Action mstate) {
		char a[][] = new char[n][n];
		Stack<Position> st = new Stack();
		int ji = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (((int) a[i][j]) == 0 && mstate.state.state[i][j] != '*') {
					ji++;
					st.push(new Position(i, j));
					a[i][j] = 1;
					while (!st.isEmpty()) {
						Position rc = st.pop();
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
				}
			}
		}
		return ji;

	}

	static Action maxValue2(Action act, long alpha, long beta, int depth) {
		if (terminalState(act.state) || act.depth >= depth || (System.nanoTime()-startTime)>((totalRemainingTime))) {// || act.depth>3
			act.utility = (int)((double)act.utility*0.75 + (double)evaluateFunction(act, true)*0.25);
			return act;
		}
		long v = minus;
		Action returningAction = null;
		List<Action> at = generateNewStates(act, true, 1.0f);// (act.state,act.utility,true);
		for (Action aw : at) {
			Action ra = minValue2(aw, alpha, beta, depth);
			if (v < ra.utility) {
				v = ra.utility;
				returningAction = ra;
			}
			if (v >= beta) {
				return returningAction;
			}
			if (alpha < v) {
				alpha = v;
			}
		}
		return returningAction;
	}

	static void printAction(Action action) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(action.state.state[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	static Action minValue2(Action act, long alpha, long beta, int depth) {
		if (terminalState(act.state) || act.depth >= depth || (System.nanoTime()-startTime)>((totalRemainingTime))) {// || act.depth>3
			act.utility = (int)((double)act.utility*0.75 + (double)evaluateFunction(act, false)*0.25);
			return act;
		}
		long v = plus;
		Action returningAction = null;
		List<Action> at = generateNewStates(act, false, 1.0f);// (act.state,act.utility,false);
		for (Action aw : at) {
			Action ra = maxValue2(aw, alpha, beta, depth);
			if (v > ra.utility) {
				v = ra.utility;
				returningAction = ra;
			}
			if (v <= alpha) {
				return returningAction;
			}
			if (beta > v) {
				beta = v;
			}
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
