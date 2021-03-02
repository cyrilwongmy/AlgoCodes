package class09_GreedyAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，
 * 会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次。
 * @author mingyan wang
 * @date 2021/2/27 7:08 PM
 */
public class Code04_BestArrange {
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 暴力枚举法
     * @param programs
     * @return 如果没有program，那么返回0。否则返回可以安排的最多场次
     */
    public static int bestArrange1(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs, 0, 0);
    }

    /**
     *
     * @param programs 目前还可以安排的会议
     * @param doneMeeting 已经安排的会议场数
     * @param currentTime 当前的时间点，当前时间点之前的会议无法安排
     * @return 最多可安排的会议
     */
    private static int process(Program[] programs, int doneMeeting, int currentTime) {
        // 如果没有可以安排的会议，那么说明都安排完了直接返回 doneMeeting
        // 隐藏baseCase：programs没有安排完，但是currentTime已经超过了所有可以安排的meeting的时间。
        if (programs.length == 0) {
            return doneMeeting;
        }
        int max = doneMeeting;
        // 处理后续可以安排的会议
        for (Program program : programs) {
            // 会议可以安排
            if (currentTime <= program.start) {
                Program[] copyProgram = copyExcept(programs, program);
                // 比较是否安排当前会议后的情况会使max增大
                max = Math.max(process(copyProgram, doneMeeting + 1, program.end), max);
            }
        }
        return max;
    }

    private static Program[] copyExcept(Program[] programs, Program program) {
        Program[] copyProgram = new Program[programs.length - 1];
        int index = 0;
        for (Program value : programs) {
            if (value != program) {
                copyProgram[index++] = value;
            }
        }
        return copyProgram;
    }


    /**
     * 贪心策略，一次遍历。如果会议的时间较早结束，优先安排该会议。
     * @param programs 可以安排的会议
     * @return 最多可以安排的会议
     */
    public static int bestArrange2(Program[] programs) {
        Arrays.sort(programs, Comparator.comparingInt(o -> o.end));
        int currentTime = 0;
        int max = 0;
        for (Program program : programs) {
            if (program.start >= currentTime) {
                max++;
                currentTime = program.end;
            }
        }
        return max;
    }


    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange1(programs) != bestArrange2(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
