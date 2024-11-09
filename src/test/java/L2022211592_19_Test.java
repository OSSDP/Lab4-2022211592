import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/*测试用例设计的总体原则
        1.等价类划分：
        将输入划分为不同的等价类，每个类中的元素对于测试来说是等价的。
        例如，对于二维数组中的元素，我们可以将0（死亡状态）和1（存活状态）视为不同的等价类。
        2.边界值分析：
        测试边界条件，例如数组的边缘元素。
        对于二维数组，边界包括第一行、最后一行、第一列和最后一列的元素。
        3.异常输入测试：
        测试代码对非法输入（如空数组或不规则面板）的处理。*/
public class L2022211592_19_Test {
    @Test
    public void testBasicFunctionality() {
        // 测试目的：验证代码能否正确实现生命游戏的规则
        Solution19 solution = new Solution19();
        int[][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        int[][] expected = {
                {0, 0, 0},
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}
        };
        solution.gameOfLife(board);
        assertArrayEquals(expected, board);
    }

    @Test
    public void testBoundaryConditions() {
        // 测试目的：测试代码在边界条件下的表现
        Solution19 solution = new Solution19();
        int[][] board = {
                {1, 1, 0}, // 第一行和第一列的边界细胞
                {0, 1, 0},
                {0, 0, 1}, // 最后一列和最后一行的边界细胞
                {0, 1, 1}  // 第二行和倒数第二列的边界细胞（虽然不是严格意义上的“边界”，但用于测试边界附近）
        };
        int[][] expected = {
                {1, 1, 0}, // 第一个细胞有2个邻居，保持存活
                {1, 1, 1}, // 第二个细胞有1个邻居，保持原样
                {0, 0, 1}, // 第三个细胞有3个邻居，复活
                {0, 1, 1}  // 第四、五个细胞分别有2个和3个邻居，保持存活和复活
        };
        solution.gameOfLife(board);
        assertArrayEquals(expected, board, "Boundary conditions test failed");
    }

    @Test
    public void testSingleCell() {
        // 测试目的：测试单个活细胞或死细胞的情况
        Solution19 solution = new Solution19();
        int[][] board = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int[][] expected = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        }; // 只有一个活细胞，且周围没有活细胞，因此该细胞死亡
        solution.gameOfLife(board);
        assertArrayEquals(expected, board);

        board = new int[][]{
                {0, 1, 1},
                {0, 1, 1},
                {0, 1, 1}
        };
        expected = new int[][]{
                {0, 1, 1},
                {1, 0, 0},
                {0, 1, 1}
        }; // 三个相邻的死细胞，其中一个细胞周围有3个活细胞，因此复活
        solution.gameOfLife(board);
        assertArrayEquals(expected, board);
    }

    @Test
    public void testEmptyBoard() {
        // 测试目的：测试空面板的情况
        Solution19 solution = new Solution19();
        int[][] board = {};
        // 预期行为：抛出异常或不做任何操作（根据具体实现）
        assertThrows(IllegalArgumentException.class, () -> {
            solution.gameOfLife(board);
        });
    }

    @Test
    public void testIrregularBoard() {
        // 测试目的：测试不规则面板的情况（如行或列数量不一致）
        Solution19 solution = new Solution19();
        int[][] board = {
                {0, 1},
                {0, 0, 1} // 非法输入，行长度不一致
        };
        // 预期行为：抛出异常或不做任何操作（根据具体实现）
        assertThrows(IllegalArgumentException.class, () -> {
            solution.gameOfLife(board);
        },"Irregular board should throw an IllegalArgumentException");
    }
}