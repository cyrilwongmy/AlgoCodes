package class08_BSTRecursiveTemplate;

import java.util.ArrayList;
import java.util.List;

public class Code09_MaxHappy {

	public static class Employee {
		public int happy;
		public List<Employee> nexts;

		public Employee(int h) {
			happy = h;
			nexts = new ArrayList<>();
		}

	}
	public static int maxHappy1(Employee boss) {
		if (boss == null) {
			return 0;
		}
		return process1(boss, false);
	}

	public static int process1(Employee cur, boolean up) {
		if (up) {
			int ans = 0;
			for (Employee next : cur.nexts) {
				ans += process1(next, false);
			}
			return ans;
		} else {
			int p1 = cur.happy;
			int p2 = 0;
			for (Employee next : cur.nexts) {
				p1 += process1(next, true);
				p2 += process1(next, false);
			}
			return Math.max(p1, p2);
		}
	}

	public static int maxHappy2(Employee boss) {
	    if (boss == null) {
	    	return 0;
		}
		Info res = process2(boss);
		return Math.max(res.yes, res.no);
	}

	public static class Info {
		public int yes;
		public int no;

		public Info(int y, int n) {
			yes = y;
			no = n;
		}
	}

	public static Info process2(Employee x) {
		if (x == null) {
			return new Info(0, 0);
		}
		int yesHappy = 0;
		int noHappy = 0;
		// x will come, nexts will not come, yes happy.
        yesHappy = x.happy;

		// x will not come, nexts can come or not come.
		// x No happy
		Info subInfo;
		int max;
		for (Employee employee : x.nexts) {
			subInfo = process2(employee);
			max = Math.max(subInfo.yes, subInfo.no);
			// TODO: crucial
			yesHappy += subInfo.no;
			noHappy += max;
		}
	    return new Info(yesHappy, noHappy);
	}

	// for test
	public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
		if (Math.random() < 0.02) {
			return null;
		}
		Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
		genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
		return boss;
	}

	// for test
	public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
		if (level > maxLevel) {
			return;
		}
		int nextsSize = (int) (Math.random() * (maxNexts + 1));
		for (int i = 0; i < nextsSize; i++) {
			Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
			e.nexts.add(next);
			genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
		}
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxNexts = 7;
		int maxHappy = 100;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
			if (maxHappy1(boss) != maxHappy2(boss)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
