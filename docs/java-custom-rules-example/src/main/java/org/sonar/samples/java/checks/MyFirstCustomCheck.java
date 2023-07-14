package org.sonar.samples.java.checks;


import com.google.common.collect.ImmutableSet;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.VariableTree;

import java.util.Set;


@Rule(key = "MyFirstCustomRule")
public class MyFirstCustomCheck extends BaseTreeVisitor implements JavaFileScanner {

  private JavaFileScannerContext context;

  private static final Set<String> FORBIDDEN_IDENTIFIERS = ImmutableSet.of("creditCard", "ccNumber", "creditCardNumber");

  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;
    scan(context.getTree());
  }

  @Override
  public void visitVariable(VariableTree tree) {
    IdentifierTree simpleName = tree.simpleName();
    if (FORBIDDEN_IDENTIFIERS.contains(simpleName.name())) {
      context.reportIssue(this, simpleName, "Use a different name than \"" + simpleName.name() + "\".");
    }
    super.visitVariable(tree);
  }
}
