package mnilg.com.countrypicker.utills;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ActivityManagers {
	private static Stack<Activity> activityStack;
	private static ActivityManagers instance;
	private static ArrayList<Activity> activityList;

	private ActivityManagers() {
		activityStack = new Stack<>();
		activityList = new ArrayList<>();
	}

	/**
	 * 单一实例
	 */
	public static ActivityManagers getAppManager() {
		if (instance == null) {
			synchronized (ActivityManagers.class) {
				if(instance == null) {
					instance = new ActivityManagers();
				}
			}
		}
		return instance;
	}

	/**
	 * 将Activity压入堆栈
	 */
	public void pushActivity(Activity activity) {
		activityStack.push(activity);
	}

	/**
	 * 将Activity从堆栈中弹出
	 * @param activity
	 */
	public void popActivity(Activity activity) {
		activityStack.remove(activity);
		removeActivity(activity);
	}

	/**
	 * 将Activity添加到List列表中
	 */
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	/**
	 * 结束List列表中的所有Activity
	 */
	public void finishAllListActivity() {
		if (activityList != null && activityList.size() > 0) {
			for (int i = 0, size = activityList.size(); i < size; i++) {
				if (null != activityList.get(i)) {
					activityList.get(i).finish();
				}
			}
			activityStack.removeAll(activityList);
			activityList.clear();
		}
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity() {
		return activityStack.peek();
	}

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity() {
		if (activityStack != null && activityStack.size() > 0) {
			Activity activity = currentActivity();
			finishActivity(activity);
		}
	}

	/**
	 * 结束指定的Activity
	 */
	private void finishActivity(Activity activity) {
		if (activity != null) {
			activity.finish();
			activityStack.remove(activity);
			activityList.remove(activity);
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	private void finishActivity(Class<?> cls) {
		if (activityStack != null && activityStack.size() > 0) {
			for (Activity activity : activityStack) {
				if (activity.getClass().equals(cls)) {
					finishActivity(activity);
				}
			}
		}
	}

	public void finishActivity(Class<?>... cls) {
		finishActivity(Arrays.asList(cls));
	}

	private void finishActivity(List<Class<?>> clsList) {
		if(clsList == null)
			return;
		for(Class<?> cls : clsList)
			finishActivity(cls);
	}

	/**
	 * pop出堆栈中的所有Activity
	 */
	public void popAllActivity() {
		if (activityStack != null && activityStack.size() > 0) {
			for (Activity activity : activityStack) {
				if (null != activity) {
					activity.finish();
				}
			}
			activityList.clear();
			activityStack.clear();
		}
	}

	/**
	 * 除了指定的Activity，都pop出堆栈
	 * @param activity
	 */
	public void popAllActivityExcept(Activity activity) {
		if(activityStack != null && activityStack.size() > 0) {
			for (Activity act : activityStack) {
				if(act != null && act != activity) {
					act.finish();
					activityStack.remove(act);
					activityList.remove(act);
				}
			}
		}
	}

	/**
	 * 清除Activity列表中的所有Activity
	 */
	public void clearAllActivity() {
		if(activityList != null && activityList.size() > 0) {
			activityList.clear();
		}
	}

	/**
	 * 移除Activity列表中的指定Activity
	 * @param activity
	 */
	private void removeActivity(Activity activity) {
		if(activity != null) {
			activityList.remove(activity);
		}
	}

	/**
	 * 获取指定类名对应的Activity实例
	 * @param cls
	 * @return
     */
	public Activity findActivity(Class<Activity> cls) {
		if (activityStack != null && activityStack.size() > 0) {
			for (Activity activity : activityStack) {
				if (activity.getClass().equals(cls)) {
					return activity;
				}
			}
		}
		return null;
	}
}