// Generated code from Butter Knife. Do not modify!
package com.tcc.locadoralivros;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.tcc.locadoralivros.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230741, "field 'edtEmail'");
    target.edtEmail = finder.castView(view, 2131230741, "field 'edtEmail'");
    view = finder.findRequiredView(source, 2131230742, "field 'edtSenha'");
    target.edtSenha = finder.castView(view, 2131230742, "field 'edtSenha'");
  }

  @Override public void unbind(T target) {
    target.edtEmail = null;
    target.edtSenha = null;
  }
}
