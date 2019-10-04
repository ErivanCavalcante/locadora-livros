// Generated code from Butter Knife. Do not modify!
package com.tcc.locadoralivros;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ListaActivity$$ViewBinder<T extends com.tcc.locadoralivros.ListaActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230739, "field 'listLivros' and method 'clickItemLista'");
    target.listLivros = finder.castView(view, 2131230739, "field 'listLivros'");
    ((android.widget.AdapterView<?>) view).setOnItemClickListener(
      new android.widget.AdapterView.OnItemClickListener() {
        @Override public void onItemClick(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.clickItemLista(p2);
        }
      });
    view = finder.findRequiredView(source, 2131230740, "field 'txtMsg'");
    target.txtMsg = finder.castView(view, 2131230740, "field 'txtMsg'");
  }

  @Override public void unbind(T target) {
    target.listLivros = null;
    target.txtMsg = null;
  }
}
