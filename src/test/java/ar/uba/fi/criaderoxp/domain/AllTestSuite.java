package ar.uba.fi.criaderoxp.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ar.uba.fi.criaderoxp.domain.model.ModelTestSuite;
import ar.uba.fi.criaderoxp.domain.service.ServiceTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { ModelTestSuite.class, ServiceTestSuite.class })
public class AllTestSuite {
}
