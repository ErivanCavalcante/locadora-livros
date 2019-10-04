// Generated code from Butter Knife. Do not modify!
package com.tcc.locadoralivros;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DadosPessoaisActivity$$ViewBinder<T extends com.tcc.locadoralivros.DadosPessoaisActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230735, "field 'txtTelefone'");
    target.txtTelefone = finder.castView(view, 2131230735, "field 'txtTelefone'");
    view = finder.findRequiredView(source, 2131230733, "field 'txtNome'");
    target.txtNome = finder.castView(view, 2131230733, "field 'txtNome'");
    view = finder.findRequiredView(source, 2131230734, "field 'txtEmail'");
    target.txtEmail = finder.castView(view, 2131230734, "field 'txtEmail'");
  }

  @Override public void unbind(T target) {
    target.txtTelefone = null;
    target.txtNome = null;
    target.txtEmail = null;
  }
}
