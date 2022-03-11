# APP内全局悬浮按钮

## 思路

通过向`DecorView`中直接添加需要悬浮的元素，以可实现悬浮

并使用`Application.registerActivityLifecycleCallbacks()`来监听所有**Activity**的生命周期，在其创建完成后进行悬浮按钮的添加

同时可以使用自定义接口来对页面进行标记，标记需要添加悬浮按钮的页面

![Preview](./preview.gif)