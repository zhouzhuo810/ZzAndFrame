package zhouzhuo810.me.zzandframe.ui.widget.zzpagerindicator.intef;

public interface IIndicatorResProvider<T> {
    
    String getTabText(T t, int position);
    
    int getSelectedIcon(int position);
    
    int getUnselectedIcon(int position);
    
}
