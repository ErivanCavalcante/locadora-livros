// Generated code from Butter Knife. Do not modify!
package com.tcc.locadoralivros;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CadastroLivroActivity$$ViewBinder<T extends com.tcc.locadoralivros.CadastroLivroActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230727, "field 'edtTitulo'");
    target.edtTitulo = finder.castView(view, 2131230727, "field 'edtTitulo'");
    view = finder.findRequiredView(source, 2131230730, "field 'edtValor'");
    target.edtValor = finder.castView(view, 2131230730, "field 'edtValor'");
    view = finder.findRequiredView(source, 2131230729, "field 'edtSinopse'");
    target.edtSinopse = finder.castView(view, 2131230729, "field 'edtSinopse'");
    view = finder.findRequiredView(source, 2131230731, "field 'barNota'");
    target.barNota = finder.castView(view, 2131230731, "field 'barNota'");
    view = finder.findRequiredView(source, 2131230728, "field 'edtAutor'");
    target.edtAutor = finder.castView(view, 2131230728, "field 'edtAutor'");
  }

  @Override public void unbind(T target) {
    target.edtTitulo = null;
    target.edtValor = null;
    target.edtSinopse = null;
    target.barNota = null;
    target.edtAutor = null;
  }
}
