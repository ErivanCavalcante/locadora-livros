// Generated code from Butter Knife. Do not modify!
package com.tcc.locadoralivros;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AlugarActivity$$ViewBinder<T extends com.tcc.locadoralivros.AlugarActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230724, "field 'txtAutor'");
    target.txtAutor = finder.castView(view, 2131230724, "field 'txtAutor'");
    view = finder.findRequiredView(source, 2131230725, "field 'txtSinopse'");
    target.txtSinopse = finder.castView(view, 2131230725, "field 'txtSinopse'");
    view = finder.findRequiredView(source, 2131230722, "field 'imgLivro'");
    target.imgLivro = finder.castView(view, 2131230722, "field 'imgLivro'");
    view = finder.findRequiredView(source, 2131230723, "field 'txtLivro'");
    target.txtLivro = finder.castView(view, 2131230723, "field 'txtLivro'");
  }

  @Override public void unbind(T target) {
    target.txtAutor = null;
    target.txtSinopse = null;
    target.imgLivro = null;
    target.txtLivro = null;
  }
}
