// Generated code from Butter Knife. Do not modify!
package com.tcc.locadoralivros;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NovoLoginActivity$$ViewBinder<T extends com.tcc.locadoralivros.NovoLoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230742, "field 'edtSenha'");
    target.edtSenha = finder.castView(view, 2131230742, "field 'edtSenha'");
    view = finder.findRequiredView(source, 2131230744, "field 'edtNome'");
    target.edtNome = finder.castView(view, 2131230744, "field 'edtNome'");
    view = finder.findRequiredView(source, 2131230745, "field 'edtTelefone'");
    target.edtTelefone = finder.castView(view, 2131230745, "field 'edtTelefone'");
    view = finder.findRequiredView(source, 2131230746, "field 'edtNovSenha'");
    target.edtNovSenha = finder.castView(view, 2131230746, "field 'edtNovSenha'");
    view = finder.findRequiredView(source, 2131230741, "field 'edtEmail'");
    target.edtEmail = finder.castView(view, 2131230741, "field 'edtEmail'");
  }

  @Override public void unbind(T target) {
    target.edtSenha = null;
    target.edtNome = null;
    target.edtTelefone = null;
    target.edtNovSenha = null;
    target.edtEmail = null;
  }
}
