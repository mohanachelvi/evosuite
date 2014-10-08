package org.evosuite.symbolic.solver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import org.evosuite.symbolic.TestCaseBuilder;
import org.evosuite.symbolic.expr.Constraint;
import org.evosuite.symbolic.solver.ConstraintSolverTimeoutException;
import org.evosuite.testcase.DefaultTestCase;
import org.evosuite.testcase.VariableReference;

import com.examples.with.different.packagename.solver.TestCaseBinaryOp;
import com.examples.with.different.packagename.solver.TestCaseCastIntToReal;
import com.examples.with.different.packagename.solver.TestCaseCastRealToInt;
import com.examples.with.different.packagename.solver.TestCaseEq;
import com.examples.with.different.packagename.solver.TestCaseGt;
import com.examples.with.different.packagename.solver.TestCaseGte;
import com.examples.with.different.packagename.solver.TestCaseLt;
import com.examples.with.different.packagename.solver.TestCaseLte;
import com.examples.with.different.packagename.solver.TestCaseMod;
import com.examples.with.different.packagename.solver.TestCaseNeq;

public class TestSolverSimpleMath {

	private static DefaultTestCase buildTestCaseAdd() throws SecurityException,
			NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference int0 = tc.appendIntPrimitive(10);
		VariableReference int1 = tc.appendIntPrimitive(0);

		Method method = TestCaseBinaryOp.class.getMethod("testAdd", int.class,
				int.class);
		tc.appendMethod(null, method, int0, int1);
		return tc.getDefaultTestCase();
	}

	private static DefaultTestCase buildTestCaseEq() throws SecurityException,
			NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference int0 = tc.appendIntPrimitive(11);
		VariableReference int1 = tc.appendIntPrimitive(11);

		Method method = TestCaseEq.class
				.getMethod("test", int.class, int.class);
		tc.appendMethod(null, method, int0, int1);
		return tc.getDefaultTestCase();
	}

	private static DefaultTestCase buildTestCaseNeq() throws SecurityException,
			NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference int0 = tc.appendIntPrimitive(1000);
		VariableReference int1 = tc.appendIntPrimitive(11);

		Method method = TestCaseNeq.class.getMethod("test", int.class,
				int.class);
		tc.appendMethod(null, method, int0, int1);
		return tc.getDefaultTestCase();
	}

	private static DefaultTestCase buildTestCaseLt() throws SecurityException,
			NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference int0 = tc.appendIntPrimitive(2);
		VariableReference int1 = tc.appendIntPrimitive(22);

		Method method = TestCaseLt.class
				.getMethod("test", int.class, int.class);
		tc.appendMethod(null, method, int0, int1);
		return tc.getDefaultTestCase();
	}

	private static DefaultTestCase buildTestCaseLte() throws SecurityException,
			NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference int0 = tc.appendIntPrimitive(2);
		VariableReference int1 = tc.appendIntPrimitive(2);

		Method method = TestCaseLte.class.getMethod("test", int.class,
				int.class);
		tc.appendMethod(null, method, int0, int1);
		return tc.getDefaultTestCase();
	}

	private static DefaultTestCase buildTestCaseGt() throws SecurityException,
			NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference int0 = tc.appendIntPrimitive(20);
		VariableReference int1 = tc.appendIntPrimitive(2);

		Method method = TestCaseGt.class
				.getMethod("test", int.class, int.class);
		tc.appendMethod(null, method, int0, int1);
		return tc.getDefaultTestCase();
	}

	private static DefaultTestCase buildTestCaseGte() throws SecurityException,
			NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference int0 = tc.appendIntPrimitive(20);
		VariableReference int1 = tc.appendIntPrimitive(2);

		Method method = TestCaseGte.class.getMethod("test", int.class,
				int.class);
		tc.appendMethod(null, method, int0, int1);
		return tc.getDefaultTestCase();
	}

	private static DefaultTestCase buildTestCaseSub() throws SecurityException,
			NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference int0 = tc.appendIntPrimitive(1);
		VariableReference int1 = tc.appendIntPrimitive(11);

		Method method = TestCaseBinaryOp.class.getMethod("testSub", int.class,
				int.class);
		tc.appendMethod(null, method, int0, int1);
		return tc.getDefaultTestCase();
	}

	private static DefaultTestCase buildTestCaseMul() throws SecurityException,
			NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference int0 = tc.appendIntPrimitive(3);
		VariableReference int1 = tc.appendIntPrimitive(6);

		Method method = TestCaseBinaryOp.class.getMethod("testMul", int.class,
				int.class);
		tc.appendMethod(null, method, int0, int1);
		return tc.getDefaultTestCase();
	}

	private static DefaultTestCase buildTestCaseMul2()
			throws SecurityException, NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference int0 = tc.appendIntPrimitive(5);
		VariableReference int1 = tc.appendIntPrimitive(2);

		Method method = TestCaseBinaryOp.class.getMethod("testMul2", int.class,
				int.class);
		tc.appendMethod(null, method, int0, int1);
		return tc.getDefaultTestCase();
	}

	public static Map<String, Object> testAdd(Solver solver) throws SecurityException,
			NoSuchMethodException, ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseAdd();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		return solution;
	}

	public static void testSub(Solver solver) throws SecurityException,
			NoSuchMethodException, ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseSub();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);
		Long var0 = (Long) solution.get("var0");
		Long var1 = (Long) solution.get("var1");

		assertEquals(var0.intValue(), var1.intValue() - 10);
	}

	public static void testMod(Solver solver) throws SecurityException,
			NoSuchMethodException, ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseMod();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);
		Long var0 = (Long) solution.get("var0");
		Long var1 = (Long) solution.get("var1");

		assertEquals(var0.intValue(), var1.intValue() % 2);
	}

	public static void testDiv(Solver solver) throws SecurityException,
			NoSuchMethodException, ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseDiv();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);
		Long var0 = (Long) solution.get("var0");
		Long var1 = (Long) solution.get("var1");

		assertEquals(var0.intValue(), var1.intValue() / 5);
	}

	public static void testMul(Solver solver) throws SecurityException,
			NoSuchMethodException, ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseMul();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);
		Long var0 = (Long) solution.get("var0");
		Long var1 = (Long) solution.get("var1");

		assertTrue(var0.intValue() != 0);
		assertEquals(var1.intValue(), var0.intValue() * 2);
	}

	public static void testMul2(Solver solver) throws SecurityException,
			NoSuchMethodException, ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseMul2();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);

		Long var0 = (Long) solution.get("var0");
		Long var1 = (Long) solution.get("var1");

		assertEquals(10, var0.intValue() * var1.intValue());

	}

	private static DefaultTestCase buildTestCaseDiv() throws SecurityException,
			NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference int0 = tc.appendIntPrimitive(4);
		VariableReference int1 = tc.appendIntPrimitive(20);

		Method method = TestCaseBinaryOp.class.getMethod("testDiv", int.class,
				int.class);
		tc.appendMethod(null, method, int0, int1);
		return tc.getDefaultTestCase();
	}

	private static DefaultTestCase buildTestCaseMod() throws SecurityException,
			NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference int0 = tc.appendIntPrimitive(0);
		VariableReference int1 = tc.appendIntPrimitive(6);

		Method method = TestCaseMod.class.getMethod("test", int.class,
				int.class);
		tc.appendMethod(null, method, int0, int1);
		return tc.getDefaultTestCase();
	}

	public static void testEq(Solver solver) throws SecurityException,
			NoSuchMethodException, ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseEq();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);
		Long var0 = (Long) solution.get("var0");
		Long var1 = (Long) solution.get("var1");

		assertEquals(var0.intValue(), var1.intValue());
	}

	public static void testNeq(Solver solver) throws SecurityException,
			NoSuchMethodException, ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseNeq();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);
		Long var0 = (Long) solution.get("var0");
		Long var1 = (Long) solution.get("var1");

		assertTrue(var0.intValue() != var1.intValue());
	}

	public static void testLt(Solver solver) throws SecurityException,
			NoSuchMethodException, ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseLt();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);
		Long var0 = (Long) solution.get("var0");
		Long var1 = (Long) solution.get("var1");

		assertTrue(var0.intValue() < var1.intValue());
	}

	public static void testLte(Solver solver) throws SecurityException,
			NoSuchMethodException, ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseLte();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);
		Long var0 = (Long) solution.get("var0");
		Long var1 = (Long) solution.get("var1");

		assertTrue(var0.intValue() <= var1.intValue());
	}

	public static void testGt(Solver solver) throws SecurityException,
			NoSuchMethodException, ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseGt();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);
		Long var0 = (Long) solution.get("var0");
		Long var1 = (Long) solution.get("var1");

		assertTrue(var0.intValue() > var1.intValue());
	}

	public static void testGte(Solver solver) throws SecurityException,
			NoSuchMethodException, ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseGte();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);
		Long var0 = (Long) solution.get("var0");
		Long var1 = (Long) solution.get("var1");

		assertTrue(var0.intValue() >= var1.intValue());
	}

	private static DefaultTestCase buildTestCaseCastRealToInt()
			throws SecurityException, NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference double0 = tc.appendDoublePrimitive(0.1);

		Method method = TestCaseCastRealToInt.class.getMethod("test",
				double.class);
		tc.appendMethod(null, method, double0);
		return tc.getDefaultTestCase();
	}

	public static void testCastRealToInt(Solver solver)
			throws SecurityException, NoSuchMethodException,
			ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseCastRealToInt();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);
		Double var0 = (Double) solution.get("var0");

		assertTrue(var0.doubleValue() != 0);
		assertTrue(var0.intValue() == 0);
	}

	public static void testCastIntToReal(Solver solver)
			throws SecurityException, NoSuchMethodException,
			ConstraintSolverTimeoutException {

		DefaultTestCase tc = buildTestCaseCastIntToReal();
		Collection<Constraint<?>> constraints = DefaultTestCaseConcolicExecutor
				.execute(tc);
		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);
		Long var0 = (Long) solution.get("var0");

		assertEquals(var0.intValue(), (int)var0.doubleValue() );
	}

	private static DefaultTestCase buildTestCaseCastIntToReal()
			throws SecurityException, NoSuchMethodException {
		TestCaseBuilder tc = new TestCaseBuilder();
		VariableReference int0 = tc.appendIntPrimitive(1);

		Method method = TestCaseCastIntToReal.class
				.getMethod("test", int.class);
		tc.appendMethod(null, method, int0);
		return tc.getDefaultTestCase();
	}
}