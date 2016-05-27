/* *********************************************************************
 * ECE351 
 * Department of Electrical and Computer Engineering 
 * University of Waterloo 
 * Term: Summer 2016 (1165)
 *
 * The base version of this file is the intellectual property of the
 * University of Waterloo. Redistribution is prohibited.
 *
 * By pushing changes to this file I affirm that I am the author of
 * all changes. I affirm that I have complied with the course
 * collaboration policy and have not plagiarized my work. 
 *
 * I understand that redistributing this file might expose me to
 * disciplinary action under UW Policy 71. I understand that Policy 71
 * allows for retroactive modification of my final grade in a course.
 * For example, if I post my solutions to these labs on GitHub after I
 * finish ECE351, and a future student plagiarizes them, then I too
 * could be found guilty of plagiarism. Consequently, my final grade
 * in ECE351 could be retroactively lowered. This might require that I
 * repeat ECE351, which in turn might delay my graduation.
 *
 * https://uwaterloo.ca/secretariat-general-counsel/policies-procedures-guidelines/policy-71
 * 
 * ********************************************************************/

package ece351.f.simplifier;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import ece351.common.ast.AndExpr;
import ece351.common.ast.AssignmentStatement;
import ece351.common.ast.ConstantExpr;
import ece351.common.ast.NaryAndExpr;
import ece351.common.ast.NaryOrExpr;
import ece351.common.ast.NotExpr;
import ece351.common.ast.OrExpr;
import ece351.common.ast.VarExpr;
import ece351.f.ast.FProgram;
import ece351.util.BaseTest351;

public final class TestSimplifier2 extends BaseTest351 {
	protected final void test(final String name, final FProgram original, final FProgram simplified) {
		System.out.println(name);
		System.out.println("original:   " + original);
		System.out.println("expected:   " + simplified);
		final FProgram simplified2 = original.simplify();
		System.out.println("actual:     " + simplified2);
		final boolean isomorphic = simplified.isomorphic(simplified2);
		System.out.println("isomorphic? " + isomorphic);
		final boolean equivalent = simplified.equivalent(simplified2);
		System.out.println("equivalent? " + equivalent);
		final boolean idempotent = simplified2.equals(simplified2.simplify());
		System.out.println("idempotent? " + idempotent);
		assertTrue(name + " unexpectedly not isomorphic. equivalent? " + equivalent, isomorphic);
		assertTrue(name + " reveals that simplify() is not idempotent.", idempotent);System.out.println();
	}
	public final static Map<String,FProgram> ORIGINAL;
	public final static Map<String,FProgram> SIMPLIFIED;
	static {
		final Map<String,FProgram> m = new TreeMap<String,FProgram>();
		final Map<String,FProgram> n = new TreeMap<String,FProgram>();
		m.put("opt0_and_nested_or", make_opt0_and_nested_or());
		n.put("opt0_and_nested_or", make_opt0_and_nested_or_simplified());
		m.put("opt0_left_parens", make_opt0_left_parens());
		n.put("opt0_left_parens", make_opt0_left_parens_simplified());
		m.put("opt0_nested_and", make_opt0_nested_and());
		n.put("opt0_nested_and", make_opt0_nested_and_simplified());
		m.put("opt0_nested_or", make_opt0_nested_or());
		n.put("opt0_nested_or", make_opt0_nested_or_simplified());
		m.put("opt0_no_parens", make_opt0_no_parens());
		n.put("opt0_no_parens", make_opt0_no_parens_simplified());
		m.put("opt0_not_or", make_opt0_not_or());
		n.put("opt0_not_or", make_opt0_not_or_simplified());
		m.put("opt0_right_parens", make_opt0_right_parens());
		n.put("opt0_right_parens", make_opt0_right_parens_simplified());
		m.put("opt1_and_true1", make_opt1_and_true1());
		n.put("opt1_and_true1", make_opt1_and_true1_simplified());
		m.put("opt1_and_true2", make_opt1_and_true2());
		n.put("opt1_and_true2", make_opt1_and_true2_simplified());
		m.put("opt1_or_false1", make_opt1_or_false1());
		n.put("opt1_or_false1", make_opt1_or_false1_simplified());
		m.put("opt1_or_false2", make_opt1_or_false2());
		n.put("opt1_or_false2", make_opt1_or_false2_simplified());
		m.put("opt2_and_false1", make_opt2_and_false1());
		n.put("opt2_and_false1", make_opt2_and_false1_simplified());
		m.put("opt2_and_false2", make_opt2_and_false2());
		n.put("opt2_and_false2", make_opt2_and_false2_simplified());
		m.put("opt2_or_true1", make_opt2_or_true1());
		n.put("opt2_or_true1", make_opt2_or_true1_simplified());
		m.put("opt2_or_true2", make_opt2_or_true2());
		n.put("opt2_or_true2", make_opt2_or_true2_simplified());
		m.put("opt3_not_false", make_opt3_not_false());
		n.put("opt3_not_false", make_opt3_not_false_simplified());
		m.put("opt3_not_not", make_opt3_not_not());
		n.put("opt3_not_not", make_opt3_not_not_simplified());
		m.put("opt3_not_true", make_opt3_not_true());
		n.put("opt3_not_true", make_opt3_not_true_simplified());
		m.put("opt4_and_complement1", make_opt4_and_complement1());
		n.put("opt4_and_complement1", make_opt4_and_complement1_simplified());
		m.put("opt4_and_complement2", make_opt4_and_complement2());
		n.put("opt4_and_complement2", make_opt4_and_complement2_simplified());
		m.put("opt4_or_complement1", make_opt4_or_complement1());
		n.put("opt4_or_complement1", make_opt4_or_complement1_simplified());
		m.put("opt4_or_complement2", make_opt4_or_complement2());
		n.put("opt4_or_complement2", make_opt4_or_complement2_simplified());
		m.put("opt4_or_complement3", make_opt4_or_complement3());
		n.put("opt4_or_complement3", make_opt4_or_complement3_simplified());
		m.put("opt5_and_dup", make_opt5_and_dup());
		n.put("opt5_and_dup", make_opt5_and_dup_simplified());
		m.put("opt5_or_dup", make_opt5_or_dup());
		n.put("opt5_or_dup", make_opt5_or_dup_simplified());
		m.put("opt6_and_or", make_opt6_and_or());
		n.put("opt6_and_or", make_opt6_and_or_simplified());
		m.put("opt6_and_or2", make_opt6_and_or2());
		n.put("opt6_and_or2", make_opt6_and_or2_simplified());
		m.put("opt6_or_and", make_opt6_or_and());
		n.put("opt6_or_and", make_opt6_or_and_simplified());
		m.put("opt6_or_no_paren", make_opt6_or_no_paren());
		n.put("opt6_or_no_paren", make_opt6_or_no_paren_simplified());
		m.put("opt7_big1", make_opt7_big1());
		n.put("opt7_big1", make_opt7_big1_simplified());
		m.put("opt7_big2", make_opt7_big2());
		n.put("opt7_big2", make_opt7_big2_simplified());
		ORIGINAL = Collections.unmodifiableMap(m);
		SIMPLIFIED = Collections.unmodifiableMap(n);
	}
	@Test public void opt0_and_nested_or() {test("opt0_and_nested_or", ORIGINAL.get("opt0_and_nested_or"), SIMPLIFIED.get("opt0_and_nested_or"));}
	protected static FProgram make_opt0_and_nested_or() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new AndExpr(new OrExpr(new OrExpr(new VarExpr("a"), new VarExpr("b")), new VarExpr("c")), new VarExpr("d"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt0_and_nested_or_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new NaryAndExpr(new NaryOrExpr(new VarExpr("a"), new VarExpr("b"), new VarExpr("c")), new VarExpr("d"))));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt0_left_parens() {test("opt0_left_parens", ORIGINAL.get("opt0_left_parens"), SIMPLIFIED.get("opt0_left_parens"));}
	protected static FProgram make_opt0_left_parens() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new OrExpr(new VarExpr("a"), new VarExpr("b")), new VarExpr("c"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt0_left_parens_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new NaryOrExpr(new VarExpr("a"), new VarExpr("b"), new VarExpr("c"))));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt0_nested_and() {test("opt0_nested_and", ORIGINAL.get("opt0_nested_and"), SIMPLIFIED.get("opt0_nested_and"));}
	protected static FProgram make_opt0_nested_and() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new AndExpr(new AndExpr(new AndExpr(new VarExpr("a"), new VarExpr("b")), new VarExpr("c")), new VarExpr("d"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt0_nested_and_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new NaryAndExpr(new VarExpr("a"), new VarExpr("b"), new VarExpr("c"), new VarExpr("d"))));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt0_nested_or() {test("opt0_nested_or", ORIGINAL.get("opt0_nested_or"), SIMPLIFIED.get("opt0_nested_or"));}
	protected static FProgram make_opt0_nested_or() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new OrExpr(new VarExpr("a"), new OrExpr(new VarExpr("b"), new VarExpr("c"))), new VarExpr("d"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt0_nested_or_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new NaryOrExpr(new VarExpr("a"), new VarExpr("b"), new VarExpr("c"), new VarExpr("d"))));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt0_no_parens() {test("opt0_no_parens", ORIGINAL.get("opt0_no_parens"), SIMPLIFIED.get("opt0_no_parens"));}
	protected static FProgram make_opt0_no_parens() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new OrExpr(new VarExpr("a"), new VarExpr("b")), new VarExpr("c"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt0_no_parens_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new NaryOrExpr(new VarExpr("a"), new VarExpr("b"), new VarExpr("c"))));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt0_not_or() {test("opt0_not_or", ORIGINAL.get("opt0_not_or"), SIMPLIFIED.get("opt0_not_or"));}
	protected static FProgram make_opt0_not_or() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new NotExpr(new AndExpr(new AndExpr(new VarExpr("c"), new VarExpr("b")), new VarExpr("a")))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt0_not_or_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new NotExpr(new NaryAndExpr(new VarExpr("a"), new VarExpr("b"), new VarExpr("c")))));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt0_right_parens() {test("opt0_right_parens", ORIGINAL.get("opt0_right_parens"), SIMPLIFIED.get("opt0_right_parens"));}
	protected static FProgram make_opt0_right_parens() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new VarExpr("a"), new OrExpr(new VarExpr("b"), new VarExpr("c")))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt0_right_parens_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new NaryOrExpr(new VarExpr("a"), new VarExpr("b"), new VarExpr("c"))));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt1_and_true1() {test("opt1_and_true1", ORIGINAL.get("opt1_and_true1"), SIMPLIFIED.get("opt1_and_true1"));}
	protected static FProgram make_opt1_and_true1() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new AndExpr(new VarExpr("a"), ConstantExpr.TrueExpr)));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt1_and_true1_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new VarExpr("a")));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt1_and_true2() {test("opt1_and_true2", ORIGINAL.get("opt1_and_true2"), SIMPLIFIED.get("opt1_and_true2"));}
	protected static FProgram make_opt1_and_true2() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new AndExpr(ConstantExpr.TrueExpr, new VarExpr("a"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt1_and_true2_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new VarExpr("a")));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt1_or_false1() {test("opt1_or_false1", ORIGINAL.get("opt1_or_false1"), SIMPLIFIED.get("opt1_or_false1"));}
	protected static FProgram make_opt1_or_false1() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new VarExpr("a"), ConstantExpr.FalseExpr)));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt1_or_false1_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new VarExpr("a")));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt1_or_false2() {test("opt1_or_false2", ORIGINAL.get("opt1_or_false2"), SIMPLIFIED.get("opt1_or_false2"));}
	protected static FProgram make_opt1_or_false2() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(ConstantExpr.FalseExpr, new VarExpr("a"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt1_or_false2_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new VarExpr("a")));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt2_and_false1() {test("opt2_and_false1", ORIGINAL.get("opt2_and_false1"), SIMPLIFIED.get("opt2_and_false1"));}
	protected static FProgram make_opt2_and_false1() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new AndExpr(new VarExpr("a"), ConstantExpr.FalseExpr)));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt2_and_false1_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", ConstantExpr.FalseExpr));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt2_and_false2() {test("opt2_and_false2", ORIGINAL.get("opt2_and_false2"), SIMPLIFIED.get("opt2_and_false2"));}
	protected static FProgram make_opt2_and_false2() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new AndExpr(ConstantExpr.FalseExpr, new VarExpr("a"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt2_and_false2_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", ConstantExpr.FalseExpr));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt2_or_true1() {test("opt2_or_true1", ORIGINAL.get("opt2_or_true1"), SIMPLIFIED.get("opt2_or_true1"));}
	protected static FProgram make_opt2_or_true1() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new VarExpr("a"), ConstantExpr.TrueExpr)));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt2_or_true1_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", ConstantExpr.TrueExpr));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt2_or_true2() {test("opt2_or_true2", ORIGINAL.get("opt2_or_true2"), SIMPLIFIED.get("opt2_or_true2"));}
	protected static FProgram make_opt2_or_true2() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(ConstantExpr.TrueExpr, new VarExpr("a"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt2_or_true2_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", ConstantExpr.TrueExpr));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt3_not_false() {test("opt3_not_false", ORIGINAL.get("opt3_not_false"), SIMPLIFIED.get("opt3_not_false"));}
	protected static FProgram make_opt3_not_false() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new NotExpr(ConstantExpr.FalseExpr)));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt3_not_false_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", ConstantExpr.TrueExpr));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt3_not_not() {test("opt3_not_not", ORIGINAL.get("opt3_not_not"), SIMPLIFIED.get("opt3_not_not"));}
	protected static FProgram make_opt3_not_not() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new NotExpr(new NotExpr(new VarExpr("a")))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt3_not_not_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new VarExpr("a")));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt3_not_true() {test("opt3_not_true", ORIGINAL.get("opt3_not_true"), SIMPLIFIED.get("opt3_not_true"));}
	protected static FProgram make_opt3_not_true() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new NotExpr(ConstantExpr.TrueExpr)));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt3_not_true_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", ConstantExpr.FalseExpr));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt4_and_complement1() {test("opt4_and_complement1", ORIGINAL.get("opt4_and_complement1"), SIMPLIFIED.get("opt4_and_complement1"));}
	protected static FProgram make_opt4_and_complement1() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new AndExpr(new VarExpr("a"), new NotExpr(new VarExpr("a")))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt4_and_complement1_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", ConstantExpr.FalseExpr));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt4_and_complement2() {test("opt4_and_complement2", ORIGINAL.get("opt4_and_complement2"), SIMPLIFIED.get("opt4_and_complement2"));}
	protected static FProgram make_opt4_and_complement2() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new AndExpr(new NotExpr(new VarExpr("a")), new VarExpr("a"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt4_and_complement2_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", ConstantExpr.FalseExpr));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt4_or_complement1() {test("opt4_or_complement1", ORIGINAL.get("opt4_or_complement1"), SIMPLIFIED.get("opt4_or_complement1"));}
	protected static FProgram make_opt4_or_complement1() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new VarExpr("a"), new NotExpr(new VarExpr("a")))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt4_or_complement1_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", ConstantExpr.TrueExpr));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt4_or_complement2() {test("opt4_or_complement2", ORIGINAL.get("opt4_or_complement2"), SIMPLIFIED.get("opt4_or_complement2"));}
	protected static FProgram make_opt4_or_complement2() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new NotExpr(new VarExpr("a")), new VarExpr("a"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt4_or_complement2_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", ConstantExpr.TrueExpr));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt4_or_complement3() {test("opt4_or_complement3", ORIGINAL.get("opt4_or_complement3"), SIMPLIFIED.get("opt4_or_complement3"));}
	protected static FProgram make_opt4_or_complement3() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new VarExpr("a"), new OrExpr(new VarExpr("b"), new NotExpr(new VarExpr("a"))))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt4_or_complement3_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", ConstantExpr.TrueExpr));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt5_and_dup() {test("opt5_and_dup", ORIGINAL.get("opt5_and_dup"), SIMPLIFIED.get("opt5_and_dup"));}
	protected static FProgram make_opt5_and_dup() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new AndExpr(new VarExpr("a"), new VarExpr("a"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt5_and_dup_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new VarExpr("a")));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt5_or_dup() {test("opt5_or_dup", ORIGINAL.get("opt5_or_dup"), SIMPLIFIED.get("opt5_or_dup"));}
	protected static FProgram make_opt5_or_dup() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new VarExpr("a"), new VarExpr("a"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt5_or_dup_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new VarExpr("a")));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt6_and_or() {test("opt6_and_or", ORIGINAL.get("opt6_and_or"), SIMPLIFIED.get("opt6_and_or"));}
	protected static FProgram make_opt6_and_or() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new AndExpr(new VarExpr("a"), new OrExpr(new VarExpr("a"), new VarExpr("b")))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt6_and_or_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new VarExpr("a")));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt6_and_or2() {test("opt6_and_or2", ORIGINAL.get("opt6_and_or2"), SIMPLIFIED.get("opt6_and_or2"));}
	protected static FProgram make_opt6_and_or2() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new AndExpr(new OrExpr(new VarExpr("a"), new VarExpr("b")), new VarExpr("a"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt6_and_or2_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new VarExpr("a")));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt6_or_and() {test("opt6_or_and", ORIGINAL.get("opt6_or_and"), SIMPLIFIED.get("opt6_or_and"));}
	protected static FProgram make_opt6_or_and() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new VarExpr("a"), new AndExpr(new VarExpr("a"), new VarExpr("b")))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt6_or_and_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new VarExpr("a")));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt6_or_no_paren() {test("opt6_or_no_paren", ORIGINAL.get("opt6_or_no_paren"), SIMPLIFIED.get("opt6_or_no_paren"));}
	protected static FProgram make_opt6_or_no_paren() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new AndExpr(new VarExpr("b"), new VarExpr("a")), new VarExpr("a"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt6_or_no_paren_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new VarExpr("a")));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt7_big1() {test("opt7_big1", ORIGINAL.get("opt7_big1"), SIMPLIFIED.get("opt7_big1"));}
	protected static FProgram make_opt7_big1() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new AndExpr(new OrExpr(new OrExpr(new VarExpr("a"), new AndExpr(new VarExpr("a"), new VarExpr("b"))), new VarExpr("b")), new OrExpr(new OrExpr(new VarExpr("b"), new VarExpr("c")), new VarExpr("a"))), new AndExpr(new VarExpr("a"), new VarExpr("a")))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt7_big1_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new NaryOrExpr(new VarExpr("a"), new VarExpr("b"))));
		assert fp.repOk();
		return fp;
	}

	@Test public void opt7_big2() {test("opt7_big2", ORIGINAL.get("opt7_big2"), SIMPLIFIED.get("opt7_big2"));}
	protected static FProgram make_opt7_big2() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new OrExpr(new OrExpr(new OrExpr(new AndExpr(new AndExpr(new VarExpr("a"), new VarExpr("b")), new VarExpr("c")), new AndExpr(new AndExpr(new VarExpr("d"), new VarExpr("c")), new VarExpr("e"))), new AndExpr(new OrExpr(new OrExpr(new VarExpr("b"), new VarExpr("c")), new AndExpr(new VarExpr("a"), new VarExpr("e"))), new OrExpr(new VarExpr("c"), new VarExpr("b")))), new VarExpr("d"))));
		assert fp.repOk();
		return fp;
	}

	protected static FProgram make_opt7_big2_simplified() {
		FProgram fp = new FProgram();
		fp = fp.append(new AssignmentStatement("x", new NaryOrExpr(new VarExpr("b"), new VarExpr("c"), new VarExpr("d"))));
		assert fp.repOk();
		return fp;
	}

}

