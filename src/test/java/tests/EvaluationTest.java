package tests;

import com.evaluation.Evaluation;
import org.junit.Test;

    public class EvaluationTest {
        Evaluation evaluation=new Evaluation();
        @Test
        public void testEvaluationAddResult() {
            evaluation.add(888, 67);
        }
        @Test
        public void testEvaluationMinusResult() {
            evaluation.minus(888, 67);
        }
        @Test
        public void testEvaluationMultiplyResult() {
            evaluation.multiply(888, 67);
        }
        @Test
        public void testEvaluationDivideResult() {
            evaluation.divide(888, 67);
        }
        @Test
        public void testEvaluationRemainderResult() {
            evaluation.remainder(67);
        }
        @Test
        public void testEvaluationCompareto0Result() {
            evaluation.compareto0(67);
        }
        @Test
        public void testEvaluationTexttoConsoleResult() {
            evaluation.texttoconsole("Summary");
        }

    }
