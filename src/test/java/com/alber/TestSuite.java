package com.alber;

import com.alber.model.RulePropertyTest;
import com.alber.model.RuleTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({AlberServiceTest.class, RuleTest.class, RulePropertyTest.class})
public class TestSuite {
}
