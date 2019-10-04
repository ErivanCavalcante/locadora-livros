// Generated code from Butter Knife. Do not modify!
package com.tcc.locadoralivros;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ListaLivrosUsuActivity$$ViewBinder<T extends com.tcc.locadoralivros.ListaLivrosUsuActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230740, "field 'txtMsg'");
    target.txtMsg = finder.castView(view, 2131230740, "field 'txtMsg'");
    view = finder.findRequiredView(source, 2131230739, "field 'listLivros' and method 'itemSelecionado'");
    target.listLivros = finder.castView(view, 2131230739, "field 'listLivros'");
    ((android.widget.AdapterView<?>) view).setOnItemClickListener(
      new android.widget.AdapterView.OnItemClickListener() {
        @Override public void onItemClick(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.itemSelecionado(p2);
        }
      });
  }

  @Override public void unbind(T target) {
    target.txtMsg = null;
    target.listLivros = null;
  }
}
