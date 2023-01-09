# Activity

Activity 类是 Android 应用的关键组件，而 Activity 的启动和组合方式则是该平台应用模型的基本组成部分。在编程范式中，应用是通过 main() 方法启动的，而 Android 系统与此不同，它会调用与其生命周期特定阶段相对应的特定回调方法来启动 Activity 实例中的代码。

## 一、概述

- 移动应用体验与桌面体验的不同之处在于，用户与应用的互动并不总是在同一位置开始，而是经常以不确定的方式开始。例如，如果您从主屏幕打开电子邮件应用，可能会看到电子邮件列表，如果您通过社交媒体应用启动电子邮件应用，则可能会直接进入电子邮件应用的邮件撰写界面。
- Activity 类的目的就是促进这种范式的实现。当一个应用调用另一个应用时，调用方应用会调用另一个应用中的 Activity，而不是整个应用。通过这种方式，Activity 充当了应用与用户互动的入口点。您可以将 Activity 实现为 Activity 类的子类。
- Activity 提供窗口供应用在其中绘制界面。此窗口通常会填满屏幕，但也可能比屏幕小，并浮动在其他窗口上面。通常，一个 Activity 实现应用中的一个屏幕。例如，应用中的一个 Activity 实现“偏好设置”屏幕，而另一个 Activity 实现“选择照片”屏幕。
- 大多数应用包含多个屏幕，这意味着它们包含多个 Activity。通常，应用中的一个 Activity 会被指定为主 Activity，这是用户启动应用时出现的第一个屏幕。然后，每个 Activity 可以启动另一个 Activity，以执行不同的操作。例如，一个简单的电子邮件应用中的主 Activity 可能会提供显示电子邮件收件箱的屏幕。主 Activity 可能会从该屏幕启动其他 Activity，以提供执行写邮件和打开邮件这类任务的屏幕。
- 虽然应用中的各个 Activity 协同工作形成统一的用户体验，但每个 Activity 与其他 Activity 之间只存在松散的关联，应用内不同 Activity 之间的依赖关系通常很小。事实上，Activity 经常会启动属于其他应用的 Activity。例如，浏览器应用可能会启动社交媒体应用的“分享”Activity。
- 要在应用中使用 Activity，您必须在应用的清单中注册关于 Activity 的信息，并且必须适当地管理 Activity 的生命周期。本文的后续内容将介绍这些主题。
- 活动（Activity）是最容易吸引到用户的地方了，它是一种可以包含用户界面的组件，主要用于和用户进行交互。

---

## 二、配置清单

- 要使用Activity就需要在清单中注册声明Activity及其特定属性。

### 2.1 声明Activity

```xml
    <manifest ... >
      <application ... >
          <activity android:name=".ExampleActivity" />
          ...
      </application ... >
      ...
    </manifest >
```

- 此元素唯一的必要属性是 android:name，该属性用于指定 Activity 的类名称。您也可以添加用于定义标签、图标或界面主题等 Activity 特征的属性。其他属性如下：

```xml
<activity android:allowEmbedded=["true" | "false"]
          android:allowTaskReparenting=["true" | "false"]
          android:alwaysRetainTaskState=["true" | "false"]
          android:autoRemoveFromRecents=["true" | "false"]
          android:banner="drawable resource"
          android:clearTaskOnLaunch=["true" | "false"]
          android:colorMode=[ "hdr" | "wideColorGamut"]
          android:configChanges=["mcc", "mnc", "locale",
                                 "touchscreen", "keyboard", "keyboardHidden",
                                 "navigation", "screenLayout", "fontScale",
                                 "uiMode", "orientation", "density",
                                 "screenSize", "smallestScreenSize"]
          android:directBootAware=["true" | "false"]
          android:documentLaunchMode=["intoExisting" | "always" |
                                  "none" | "never"]
          android:enabled=["true" | "false"]
          android:excludeFromRecents=["true" | "false"]
          android:exported=["true" | "false"]
          android:finishOnTaskLaunch=["true" | "false"]
          android:hardwareAccelerated=["true" | "false"]
          android:icon="drawable resource"
          android:immersive=["true" | "false"]
          android:label="string resource"
          android:launchMode=["standard" | "singleTop" |
                              "singleTask" | "singleInstance" | "singleInstancePerTask"]
          android:lockTaskMode=["normal" | "never" |
                              "if_whitelisted" | "always"]
          android:maxRecents="integer"
          android:maxAspectRatio="float"
          android:multiprocess=["true" | "false"]
          android:name="string"
          android:noHistory=["true" | "false"]  
          android:parentActivityName="string" 
          android:persistableMode=["persistRootOnly" | 
                                   "persistAcrossReboots" | "persistNever"]
          android:permission="string"
          android:process="string"
          android:relinquishTaskIdentity=["true" | "false"]
          android:resizeableActivity=["true" | "false"]
          android:screenOrientation=["unspecified" | "behind" |
                                     "landscape" | "portrait" |
                                     "reverseLandscape" | "reversePortrait" |
                                     "sensorLandscape" | "sensorPortrait" |
                                     "userLandscape" | "userPortrait" |
                                     "sensor" | "fullSensor" | "nosensor" |
                                     "user" | "fullUser" | "locked"]
          android:showForAllUsers=["true" | "false"]
          android:stateNotNeeded=["true" | "false"]
          android:supportsPictureInPicture=["true" | "false"]
          android:taskAffinity="string"
          android:theme="resource or theme"
          android:uiOptions=["none" | "splitActionBarWhenNarrow"]
          android:windowSoftInputMode=["stateUnspecified",
                                       "stateUnchanged", "stateHidden",
                                       "stateAlwaysHidden", "stateVisible",
                                       "stateAlwaysVisible", "adjustUnspecified",
                                       "adjustResize", "adjustPan"] >   
    . . .
</activity>
```

1. `android:allowEmbedded`表示该 activity 可作为其他 activity 的嵌入式子项启动。此属性尤其适用于子项位于其他 Activity 所拥有容器（如 Display）中的情况。例如，用于 Wear 自定义通知的 activity 必须声明此属性，以便 Wear 在其位于另一进程内的上下文流中显示 activity。此属性的默认值为 `false`。
2. `android:allowTaskReparenting`当下一次将启动 Activity 的任务转至前台时，Activity 是否能从该任务转移至与其有相似性的任务 -“`true`”表示可以转移，“`false`”表示仍须留在启动它的任务处。如果未设置该属性，则对 Activity 应用由 `application` 元素的相应 `allowTaskReparenting` 属性所设置的值。默认值为“`false`”。正常情况下，activity 启动时会与启动它的任务关联，并在其整个生命周期中一直留在该任务处。当不再显示现有任务时，您可以使用该属性强制 activity 将其父项更改为与其有相似性的任务。该属性通常用于将应用的 activity 转移至与该应用关联的主任务。例如，如果电子邮件消息包含网页链接，则点击该链接会调出可显示该网页的 activity。该 activity 由浏览器应用定义，但作为电子邮件任务的一部分启动。如果将该 activity 的父项更改为浏览器任务，则它会在浏览器下一次转至前台时显示，在电子邮件任务再次转至前台时消失。activity 的相似性由 `taskAffinity` 属性定义。通过读取任务根 activity 的相似性即可确定任务的相似性。因此，按照定义，根 activity 始终位于具有同一相似性的任务中。由于具有“`singleTask`”或“`singleInstance`”启动模式的 activity 只能位于任务的根上，因此更改父项仅限于“`standard`”和“`singleTop`”模式。（可参阅 `launchMode` 属性。）
3. `android:alwaysRetainTaskState`系统是否始终保持 Activity 所在任务的状态 -“`true`”表示是，“`false`”表示允许系统在特定情况下将任务重置到其初始状态。默认值为“`false`”。该属性只对任务的根 activity 有意义；所有其他 activity 均忽略该属性。正常情况下，当用户从主屏幕重新选择某个任务时，系统会在特定情况下清除该任务（从根 Activity 上的堆栈中移除所有 Activity）。通常，如果用户在一段时间（如 30 分钟）内未访问任务，系统会执行此操作。不过，如果该属性的值是“`true`”，则无论用户如何返回任务，该任务始终会显示最后一次的状态。例如，该属性非常适用于网络浏览器这类应用，因为其中存在大量用户不愿丢失的状态（如多个打开的标签页）。
4. `android:autoRemoveFromRecents`由具有该属性的 Activity 启动的任务是否一直保留在概览屏幕中，直至任务中的最后一个 Activity 完成为止。若为 `true`，则自动从概览屏幕中移除任务。它会替换调用方使用的 `FLAG_ACTIVITY_RETAIN_IN_RECENTS`。它必须是布尔值“`true`”或“`false`”。
5. `android:banner`一种可绘制资源，可为其关联项提供扩展图形横幅。可与 `<activity>` 标记联用，为特定 activity 提供默认横幅；也可与 [`<application>`](https://developer.android.google.cn/guide/topics/manifest/application-element) 标记联用，为所有应用 activity 提供横幅。系统在 Android TV 主屏幕中使用横幅来代表应用。由于横幅只显示在主屏幕中，因此只能由具有能够处理 `CATEGORY_LEANBACK_LAUNCHER` Intent 的 Activity 的应用指定。必须将此属性设为对包含图片的可绘制资源（例如 `"@drawable/banner"`）的引用。没有默认横幅。
6. `android:clearTaskOnLaunch`是否每当从主屏幕重新启动任务时都从中移除根 activity 之外的所有 activity -“`true`”表示始终将任务清除到只剩其根 activity；“`false`”表示不做清除。默认值为“`false`”。该属性只对启动新任务的 activity（根 activity）有意义；任务中的所有其他 activity 均可忽略该属性。若值为“`true`”，则每次当用户再次启动任务时，无论用户最后在任务中正在执行哪个 Activity，也无论用户是使用“返回”还是“主屏幕”按钮离开，系统都会将用户转至任务的根 Activity。当值为“`false`”时，可在某些情况下清除任务中的 Activity，但也有例外。例如，假设用户从主屏幕启动 Activity P，然后从该处转到 Activity Q。接着，该用户按下 *主屏幕* ，然后返回到 Activity P。正常情况下，用户将看到 Activity Q，因为这是其最后在 P 的任务中所执行的 Activity。不过，如果 P 将此标志设置为“`true`”，当用户从主屏幕启动 activity P 时，系统会移除 P 上方的所有 activity（在本例中为 Q）。因此用户在返回任务时只会看到 P。如果该属性和 `allowTaskReparenting` 的值均为“`true`”，则如上所述，任何可更改父项的 Activity 都将转移至与其有相似性的任务；而其余 Activity 随即会被移除。如果未设置 `FLAG_ACTIVITY_RESET_TASK_IF_NEEDED`，系统将忽略此属性。
7. `android:colorMode`请求在兼容设备上以广色域模式显示 Activity。 在广色域模式下，窗口可以在 `<a href="https://developer.android.google.cn/reference/android/graphics/ColorSpace.Named#SRGB">SRGB</a>` 色域之外进行渲染，从而显示更鲜艳的色彩。如果设备不支持广色域渲染，则此属性无效。
8. `android:configChanges`列出 Activity 将自行处理的配置变更。在运行时发生配置变更时，默认情况下会关闭 activity 并将其重启，但使用该属性声明配置将阻止 activity 重启。相反，activity 会保持运行状态，并且系统会调用其 `onConfigurationChanged()` 方法。

任何或所有下列字符串均是该属性的有效值。若有多个值，则使用“`|`”进行分隔，例如“`locale|navigation|orientation`”。


| 值                       | 说明                                                                                                                                                                                                                                    |
| -------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| “`density`”            | 显示密度发生变更 - 用户可能已指定不同的显示比例，或者有不同的显示现处于活跃状态。在 API 级别 24 中引入。                                                                                                                                |
| “`fontScale`”          | 字体缩放系数发生变更 - 用户已选择新的全局字号。                                                                                                                                                                                         |
| “`keyboard`”           | 键盘类型发生变更 - 例如，用户插入外置键盘。                                                                                                                                                                                             |
| “`keyboardHidden`”     | 键盘无障碍功能发生变更 - 例如，用户显示硬键盘。                                                                                                                                                                                         |
| “`layoutDirection`”    | 布局方向发生变更 - 例如，自从左至右 (LTR) 更改为从右至左 (RTL)。在 API 级别 17 中引入。                                                                                                                                                 |
| “`locale`”             | 语言区域发生变更 - 用户已为文本选择新的显示语言。                                                                                                                                                                                       |
| “`mcc`”                | IMSI 移动设备国家/地区代码 (MCC) 发生变更 - 检测到 SIM 并更新 MCC。                                                                                                                                                                     |
| “`mnc`”                | IMSI 移动设备网络代码 (MNC) 发生变更 - 检测到 SIM 并更新 MNC。                                                                                                                                                                          |
| “`navigation`”         | 导航类型（轨迹球/方向键）发生变更。（这种情况通常不会发生。）                                                                                                                                                                           |
| “`orientation`”        | 屏幕方向发生变更 - 用户旋转设备。<br />注意 ：如果应用面向 Android 3.2（API 级别 13）或更高版本的系统，则还应声明 `"screenLayout"` 和 `"screenSize"` 配置，因为当设备在纵向模式与横向模式之间切换时，屏幕布局和屏幕大小可能会发生变化。 |
| “`screenLayout`”       | 屏幕布局发生变更 - 现处于活跃状态的可能是其他显示模式。                                                                                                                                                                                 |
| “`screenSize`”         | 当前可用屏幕尺寸发生变更。该值表示目前可用尺寸相对于当前宽高比的变更，当用户在横向模式与纵向模式之间切换时，它便会发生变更。在 API 级别 13 中引入。                                                                                     |
| “`smallestScreenSize`” | 物理屏幕尺寸发生变更。该值表示与方向无关的尺寸变更，因此它只有在实际物理屏幕尺寸发生变更（如切换到外部显示器）时才会变化。对此配置所作变更对应smallestWidth 配置的变化。在 API 级别 13 中引入。                                         |
| “`touchscreen`”        | 触摸屏发生变更。（这种情况通常不会发生。）                                                                                                                                                                                              |
| “`uiMode`”             | 界面模式发生变更 - 用户已将设备置于桌面或车载基座，或者夜间模式发生变更。在 API 级别 8 中引入。                                                                                                                                         |

所有这些配置变更都可能影响应用所看到的资源值。因此，调用 `onConfigurationChanged()` 时，通常有必要再次检索所有资源（包括视图布局、可绘制对象等），以正确处理变更。

9. `android:directBootAware`Activity 是否可感知直接启动 (direct-boot)；即，它是否可以在用户解锁设备之前运行。

> **注意** ：在直接启动期间，应用中的 Activity 仅可访问存储在设备保护存储区的数据。

默认值为 `"false"`。

10. `android:documentLaunchMode`指定每次启动任务时，应如何向其添加新的 Activity 实例。该属性允许用户让多个来自同一应用的文档出现在概览屏幕中。该属性具有四个值，在用户使用应用打开文档时分别会产生以下效果：


| 值                 | 说明                                                                                                                                                                                                                                                                                                  |
| -------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| “`intoExisting`” | 系统会搜索基本 Intent 的`ComponentName` 和数据 URI 与启动 Intent 的这些内容相匹配的任务。如果发现此类任务，系统会将其清除，并在根 Activity 收到对 `onNewIntent` 的调用时自行重启。 如果未发现此类任务，系统会创建新任务。                                                                             |
| “`always`”       | Activity 为文档创建新任务，即便文档已经打开。 这与同时设置`FLAG_ACTIVITY_NEW_DOCUMENT` 和 `FLAG_ACTIVITY_MULTIPLE_TASK` 标志的效果相同。                                                                                                                                                              |
| “`none`”         | 该 Activity 不会为 Activity 创建新任务。这是默认值，它只会在已设置`FLAG_ACTIVITY_NEW_TASK` 时创建新任务。 概览屏幕将按其默认方式处理此 Activity：为应用显示单个任务，该任务将从用户上次调用的任意 Activity 开始继续执行。                                                                             |
| “`never`”        | 即使 intent 包含`FLAG_ACTIVITY_NEW_DOCUMENT`，该 activity 也不会启动到新文档中。设置此值会替换 `FLAG_ACTIVITY_NEW_DOCUMENT` 和 `FLAG_ACTIVITY_MULTIPLE_TASK` 标志的行为（如果在 Activity 中设置其中一个标志），并且概览屏幕将为应用显示单个任务，该任务将从用户上次调用的任意 Activity 开始继续执行。 |

> **注意** ：如果值不是“`none`”和“`never`”，必须使用 `launchMode="standard"` 定义 Activity。如果未指定此属性，系统会使用 `documentLaunchMode="none"`。

11. `android:enabled`系统是否可实例化 Activity -“`"true"`”表示可以，“`false`”表示不可以。默认值为“`true`”。`<application>`元素具有自己的 `enabled` 属性，该属性适用于所有应用组件，包括 Activity。只有在 `<application>` 和 `<activity>` 属性都为“`true`”（因为它们都默认使用该值）时，系统才能将 Activity 实例化。如果其中任一属性为“`false`”，则无法实例化。
12. `android:excludeFromRecents`是否应从最近使用的应用列表（即概览屏幕）中排除该 Activity 启动的任务。换言之，当该 Activity 是新任务的根 Activity 时，此属性确定最近使用的应用列表中是否应出现该任务。如果应将任务从列表中排除，则设置“`true`”；如果应将任务包含在列表中，则设置“`false`”。默认值为“`false`”。
13. `android:exported`此元素设置 Activity 是否可由其他应用的组件启动：

* 如果设为“`true`”，那么 Activity 可由任何应用访问，并且可通过其确切类名称启动。
* 如果设为“`false`”，则 Activity 只能由同一应用的组件、使用同一用户 ID 的不同应用或具有特权的系统组件启动。 没有 intent 过滤器时，这是默认值。

如果应用中的 Activity 包含 intent 过滤器，请将此元素设置为“`true`”，以允许其他应用启动该 Activity。例如，假设 Activity 是应用的主要 Activity，并且包含 `category`“`android.intent.category.LAUNCHER`”。

如果此元素设为“`false`”，并且应用尝试启动该 Activity，系统会抛出 `ActivityNotFoundException`。

此属性并非是限制 Activity 向其他应用公开的唯一方式。权限还可用于限制可调用 Activity 的外部实体（请参阅 `permission` 属性）。

14. `android:finishOnTaskLaunch`每当用户再次启动 Activity 的任务（在主屏幕上选择任务）时，是否应关闭（完成）根 Activity 之外的现有 Activity 实例 -“`true`”表示应关闭，“`false`”表示不应关闭。默认值为“`false`”。如果此属性和 `allowTaskReparenting` 的值均为“`true`”，则优先使用此属性。系统会忽略 Activity 的相似性。系统不会更改 Activity 的父项，而是将其销毁。如果未设置 `FLAG_ACTIVITY_RESET_TASK_IF_NEEDED`，系统将忽略此属性。
15. `android:hardwareAccelerated`是否应为此 Activity 启用硬件加速渲染 -“`true`”表示应启用，“`false`”表示不应启用。默认值为“`false`”。自 Android 3.0 开始，应用可使用经硬件加速的 OpenGL 渲染器，从而提高许多常见 2D 图形运算的性能。启用硬件加速的渲染程序后，Canvas、Paint、Xfermode、ColorFilter、Shader 和 Camera 中的大多数操作都会加速。这样可使动画和滚动更流畅并且可提高整体响应能力，即使对于没有明确使用框架的 OpenGL 库的应用也是如此。启用硬件加速需要更多资源，因此应用会占用更多内存。请注意，并非所有 OpenGL 2D 操作都会加速。如果您启用硬件加速的渲染程序，请对应用进行测试，以确保它可以毫无错误地使用该渲染程序。
16. `android:icon`表示 Activity 的图标。当需要在屏幕上呈现 activity 时，系统会向用户显示该图标。例如，启动器窗口中会显示启动任务的 Activity 所用图标。该图标通常附带标签（请参阅 [`android:label`](https://developer.android.google.cn/guide/topics/manifest/activity-element#label) 属性）。必须将此属性设为对包含图片定义的可绘制资源的引用。如果未设置此属性，则改用为整个应用指定的图标（请参阅 `icon` 属性）。Activity 的图标（无论是在此处设置，还是由 `<application>` 元素设置）- 同时也是 Activity 所有 Intent 过滤器的默认图标（请参阅 `<intent-filter>` 元素的 `icon` 属性）。
17. `android:immersive`为当前 Activity 进行沉浸模式设置。如果在应用清单文件条目中为此 Activity 将 `android:immersive` 属性设置为 `true`，则 `ActivityInfo.flags` 成员会始终设置其 `FLAG_IMMERSIVE` 位（即便在运行时使用 `setImmersive()` 方法更改沉浸模式）。
18. `android:label`一种可由用户读取的 Activity 标签。在必须向用户呈现 Activity 时，屏幕上会显示此标签。此标签通常与 Activity 图标一并显示。如果未设置此属性，则改用整个应用的标签集（请参阅 `<application>` 元素的 `label` 属性）。Activity 的标签（无论是在此处设置，还是由 `<application>` 元素设置）同时也是 Activity 所有 Intent 过滤器的默认标签（请参阅 `<intent-filter>` 元素的 `label` 属性）。此标签应设置为对字符串资源的引用，以便可以像界面中的其他字符串一样进行本地化。不过，为了方便您开发应用，也可以将其设为原始字符串。
19. `android:launchMode`有关应如何启动 activity 的指令。共有五种模式可与 `<a href="https://developer.android.google.cn/reference/android/content/Intent">Intent</a>` 对象中的 activity 标记（`FLAG_ACTIVITY_*` 常量）协同工作，以确定在调用 activity 处理 intent 时应执行的操作。它们是：

> "`standard`"
> "`singleTop`"
> "`singleTask`"
> "`singleInstance`"
> "`singleInstancePerTask`"

默认模式为“`standard`”。

如下表所示，这些模式可分为两大类：“`standard`”和“`singleTop`”activity 为一类，“`singleTask`”“`singleInstance`”和“`singleInstancePerTask`”activity 为另一类。启动模式为“`standard`”或“`singleTop`”的 activity 可以多次实例化。实例可归属任何任务，并且可位于 activity 任务中的任何位置。通常，它们会启动到名为 `startActivity()` 的任务中（除非 intent 对象包含 `FLAG_ACTIVITY_NEW_TASK` 指令，在此情况下会选择其他任务 - 请参阅 taskAffinity 属性）。

相比之下，"`singleTask`" "`singleInstance`" 和 "`singleInstancePerTask`" activity 的行为有所不同。 “`singleInstancePerTask`”始终位于 activity 任务的根位置。此外，设备一次只能保留一个 "`singleInstance`" activity 实例，而 "`singleInstancePerTask`" activity 在 `FLAG_ACTIVITY_MULTIPLE_TASK` 或 `FLAG_ACTIVITY_NEW_DOCUMENT` 已设置的情况下，在不同的任务中可以多次实例化。启动模式为 "`singleTask`" 的 activity 结合了 "`singleInstance`" 和 "`singleInstancePerTask`" 的行为：activity 可以多次实例化，并且可以位于具有相同 `taskAffinity` 的任务中的任意位置。同时，设备只能保留一个用于在 activity 任务的根位置查找 "`singleTask`" activity 的任务。

“`standard`”和“`singleTop`”模式只有一个不同之处：每次“`standard`”activity 有一个新的 intent 时，系统都会创建类的新实例来响应该 intent。每个实例处理单个 Intent。同样地，您也可以创建新的“`singleTop`”Activity 实例来处理新的 Intent。不过，如果目标任务的 Activity 堆栈顶部已有一个 Activity 实例，则该实例会（通过调用 `onNewIntent()`）接收新的 Intent；此时不会创建新实例。在其他情况下（例如，如果“`singleTop`”activity 的某个现有实例虽在目标任务内，但未处于堆栈顶部，或者虽然位于堆栈顶部，但不在目标任务中），系统会创建新实例并将其送入堆栈。

同样地，如果您 向上导航 到当前堆栈上的某个 Activity，则该行为由父 Activity 的启动模式决定。如果父 Activity 有启动模式 `singleTop`（或者 `up` Intent 包含 `FLAG_ACTIVITY_CLEAR_TOP`），则系统会将该父项置于堆栈顶部，并保留其状态。导航 Intent 由父 Activity 的 `onNewIntent()` 方法接收。如果父 activity 有启动模式 `standard`（并且 `up` intent 不包含 `FLAG_ACTIVITY_CLEAR_TOP`），则系统会将当前 activity 及其父项同时送出堆栈，并创建新的父 activity 实例来接收导航 intent。

“`singleInstance`”模式也与“`singleTask`”和“`singleInstancePerTask`”有一个不同之处：具有“`singleTask`”或“`singleInstancePerTask`”启动模式的 activity 允许其他 activity（必须是“`standard`”和“`singleTop`”activity）成为其任务的一部分。另一方面，“`singleInstance`”activity 不允许任何其他 activity 成为其任务的一部分；它必须是任务中唯一的 activity。如果它启动另一个 activity，则系统会将该 activity 分配给其他任务，就如同 intent 中包含 `FLAG_ACTIVITY_NEW_TASK` 一样。


| 使用场景                            | 启动模式                  | 多个实例？ | 备注                                                                                                                                                                                                                              |
| ------------------------------------- | --------------------------- | ------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 大多数 Activity 的正常启动          | “`standard`”            | 是         | 默认。系统始终会在目标任务中创建新的 Activity 实例，并向其传送 Intent。                                                                                                                                                           |
|                                     | “`singleTop`”           | 视情况而定 | 如果目标任务的顶部已存在 Activity 实例，则系统会通过调用该实例的`onNewIntent()` 方法向其传送 Intent，而非创建新的 Activity 实例。                                                                                                 |
| 专用启动<br/>（不建议用作常规用途） | "`singleTask`"            | 视情况而定 | 系统会在新任务的根位置创建 activity，或将该 activity 放置在具有相同 affinity 的现有任务上。 如果任务的根位置已存在 activity 实例，则系统会通过调用现有实例的`onNewIntent()` 方法（而非创建新的 activity 实例），向其传送 intent。 |
|                                     | "`singleInstance`"        | 否         | 与`singleTask"` 相似，唯一不同的是系统不会将任何其他 Activity 启动到包含该实例的任务中。该 activity 始终是其任务中的唯一 activity。                                                                                               |
|                                     | "`singleInstancePerTask`" | 视情况而定 | 此 activity 只能作为任务的根 activity、作为创建该任务的第一个 activity 运行，因此在任务中该 activity 只有一个实例；但是 activity 可以在不同的任务中多次实例化。                                                                   |

如上表所示，`standard` 是默认模式，适用于大多数类型的 activity。对众多类型的 activity 而言，`SingleTop` 也是常见且有用的启动模式。其他模式（`singleTask`、`singleInstance` 和 `singleInstancePerTask`）不适用于大多数应用，因为它们所形成的交互模式可能让用户感到陌生，并且与大多数其他应用差别较大。

无论选择哪种启动模式，在 activity 启动期间以及使用“返回”按钮从其他 activity 和任务返回该 activity 时，请务必对其进行易用性测试。

20. `android:lockTaskMode`确定当设备在 **锁定任务模**式 下运行时，系统如何显示此 Activity。Android 可以类似于 Kiosk 的沉浸式方式（称为锁定任务模式）运行任务。当系统在锁定任务模式下运行时，设备用户通常无法查看通知、访问非许可名单内应用或返回主屏幕（除非主页应用已列入许可名单）。只有经设备政策控制器 (DPC) 列入许可名单后，应用才能在系统处于锁定任务模式时运行。但是，系统和 **特权应用** 无需列入许可名单便可在锁定任务模式下运行。

此属性的值可为以下任一 [`R.attr.lockTaskMode`](https://developer.android.google.cn/reference/android/R.attr#lockTaskMode) 字符串值：


| 值                  | 说明                                                                                                                                                                                                                                                                                                                                                                |
| --------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `"normal"`          | **默认值。** 这是默认值。任务不会启动到锁定任务模式中，但您可通过调用 [`startLockTask()`](https://developer.android.google.cn/reference/android/app/Activity#startLockTask()) 将其置于此模式中。                                                                                                                                                                    |
| `"never"`           | 任务不会启动到锁定任务模式中，且设备用户无法在概览屏幕中固定这些任务。<br />**注意** ：此模式仅可用于系统应用和特权应用。系统会将带有此值的非特权应用视为 `normal`。                                                                                                                                                                                                |
| `"if__whitelisted"` | 如果 DPC 使用`DevicePolicyManager.setLockTaskPackages()` 授权此软件包，则此模式等同于 `always`，区别在于如果此 Activity 是最后一个锁定任务，则其需要调用 `stopLockTask()` 才能达成目的。如果 DPC 不对此软件包授权，则此模式等同于 `normal`。                                                                                                                      |
| `"always"`          | 位于此 Activity 根位置的任务始终会启动到锁定任务模式中。如果系统在此任务启动时已处于锁定模式中，则新任务将在当前任务上方启动。在锁定模式下启动的任务可通过调用[`finish()`](https://developer.android.google.cn/reference/android/app/Activity#finish()) 退出此模式。 <br />**注意** ：此模式仅可用于系统应用和特权应用。系统会将带有此值的非特权应用视为 `normal`。 |

该属性是 API 级别 23 中的新增属性。

21. `android:maxRecents`**概览屏幕**中位于此 Activity 根位置处的最大任务数。达到该条目数时，系统会从概览屏幕中移除近期最少使用的实例。有效值为 1 至 50（内存较小的设备使用 25）；0 为无效值。该值必须是整数，例如 50。默认值为 16。
22. `android:maxAspectRatio`activity 支持的最大宽高比。如果应用在具有较大宽高比的设备上运行，则系统会自动为其添加黑边，不会使用屏幕的某些部分，以便应用可按其指定的最大宽高比运行。

最大宽高比表示为设备长边除以短边的商（小数形式）。例如，如果最大宽高比为 7:3，则此属性的值应设为 2.33。

在非穿戴式设备上，此属性的值需设为大于或等于 1.33。在穿戴式设备上，该值必须大于或等于 1.0。否则，系统将忽略此设定值。

> **注意** ：如果 activity 已将 `resizeableActivity` 设置为 true，则系统会忽略此属性，因为这意味着此 activity 支持任何尺寸。

23. `android:multiprocess`是否可将 activity 实例启动到启动该实例的组件进程内 - `true` 表示可以，`false` 表示不可以。默认值为“`false`”。正常情况下，新的 Activity 实例会启动到定义该实例的应用进程内，因此所有 Activity 实例都在同一进程内运行。不过，如果将该标志设置为“`true`”，则 Activity 实例便可在多个进程内运行，以便系统在任何使用实例的地方创建实例（前提是权限允许这样做），但这几乎毫无必要。
24. `android:name`实现 Activity 的类的名称，是 `Activity` 的子类。属性值应该是一个完全限定类名（如“`com.example.project.ExtracurricularActivity`”）。不过，作为一种简写形式，如果名称的第一个字符是句点（例如“`.ExtracurricularActivity`”），则会将其附加到 `build.gradle` 文件中指定的**命名空间**。发布应用后，**不能更改此名称**（除非您已设置 `android:exported="false"`）。没有默认值。必须指定此名称。
25. `android:noHistory`当用户离开 Activity 且屏幕上不再显示该 Activity 时，是否应从 Activity 堆栈中将其移除并完成（调用其 `finish()` 方法）-“`true`”表示应将其完成，“`false`”表示不应将其完成。默认值为“`false`”。“`true`”一值表示 Activity 不会留下历史跟踪记录。它不会留在任务的 activity 堆栈内，因此用户将无法返回该 activity。在此情况下，如果您通过启动另一个 Activity 来获取该 Activity 的结果，系统永远不会调用 `onActivityResult()`。该属性是 API 级别 3 中的新增属性。
26. `android:parentActivityName`Activity 逻辑父项的类名称。此处的名称必须与为相应 `<activity>` 元素的 [`android:name`](https://developer.android.google.cn/guide/topics/manifest/activity-element#nm) 属性所指定的类名称一致。系统会读取该属性，以确定当用户按下操作栏中的“向上”按钮时应该启动哪一个 Activity。系统还可利用这些信息，通过 `TaskStackBuilder` 合成 Activity 的返回堆栈。

如要支持 API 级别 4 至 16，您还可使用为 `"android.support.PARENT_ACTIVITY"` 指定值的 `<meta-data>` 元素来声明父 Activity。例如：

```xml
<activity
    android:name="com.example.app.ChildActivity"
    android:label="@string/title_child_activity"
    android:parentActivityName="com.example.app.MainActivity">
    <!-- Parent activity meta-data to support API level 4+ -->
    <meta-data
        android:name="android.support.PARENT_ACTIVITY"
        android:value="com.example.app.MainActivity"/>
</activity>
```

如需了解通过声明父 Activity 来支持向上导航的详细信息，请阅读**提供向上导航**。

该属性是 API 级别 16 中的新增属性。

27. `android:persistableMode`定义当设备重启时，如何在所处的任务中保留 activity 实例。

如果任务的根 activity 将此属性的值设置为 `persistRootOnly`，则仅保留根 activity。否则，系统会检查任务**返回堆栈**中较高位置的 activity，并在其中保留任何将此属性的值设为 `persistAcrossReboots` 的 activity。

如果使用此属性，您必须将其值设置为以下某个值：


| 值                     | 说明                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `persistRootOnly`      | **默认值。** 系统重启时会保留 Activity 任务，但仅使用根 Activity 的启动 Intent。如果应用的启动 Intent 加载应用的根 Activity，则此 Activity 不会接收 `<a href="https://developer.android.google.cn/reference/android/os/PersistableBundle">PersistableBundle</a>` 对象。 因此，在设备重启时，请勿使用 `<a href="https://developer.android.google.cn/reference/android/app/Activity#onSaveInstanceState(android.os.Bundle,%20android.os.PersistableBundle)">onSaveInstanceState()</a>` 保留应用根 Activity 的状态。 **注意** ：只有在应用的根 Activity 上设置此属性值后，该值才会影响应用的行为。                                                                                                                                         |
| `persistAcrossReboots` | 系统将保留此 Activity 的状态，以及每个高于**返回堆栈**且已将自身的`persistableMode` 属性设置为 `persistAcrossReboots` 的 Activity 的状态。如果 Activity 未将 `persistableMode` 属性设置为 `persistAcrossReboots`，或者使用 `Intent.FLAG_ACTIVITY_NEW_DOCUMENT` 标志启动，则系统不会保留此 Activity 及高于返回堆栈的所有 Activity。当 Intent 加载应用中将 `persistableMode` 属性设置为 `persistAcrossReboots` 的 Activity 时，此 Activity 将在其 `onCreate()` 方法中接收 `PersistableBundle` 对象。因此，您可使用 `onSaveInstanceState()` 在跨设备重新启动时保留 Activity 的状态，只要其 `persistableMode` 属性设置为 `persistAcrossReboots`。 **注意** ：即使在应用根 activity 之外的 activity 上设置此属性值，该值也会影响应用的行为。 |
| `persistNever`         | 系统不会保留 Activity 的状态。**注意** ：只有在应用的根 Activity 上设置此属性值后，该值才会影响应用的行为。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |

该属性是 API 级别 21 中的新增属性。

28. `android:permission`启动 Activity 或以其他方式使 Activity 响应 Intent 时，客户端必须具备的权限的名称。如果系统尚未向 `startActivity()` 或 `startActivityForResult()` 的调用方授予指定权限，则其 intent 将不会传递给 activity。如果未设置该属性，则对 Activity 应用由 `<application>` 元素的 `permission` 属性所设置的权限。如果二者均未设置，则 Activity 不受权限保护。
29. `android:process`应在其中运行 Activity 的进程的名称。正常情况下，应用的所有组件均以为应用创建的默认进程名称运行，您无需使用该属性。但如有必要，您可以使用该属性替换默认进程名称，以便将应用组件散布到多个进程中。如果为该属性分配的名称以冒号（“:”）开头，则系统会在需要时创建应用专用的新进程，并且 Activity 会在该进程中运行。如果进程名称以小写字符开头，则 Activity 将在采用该名称的全局进程中运行，前提是它具有相应权限。这样，不同应用中的组件就可以共享进程，从而减少资源使用量。`<application>` 元素的 `process` 属性可为所有组件设置一个不同的默认进程名称。
30. `android:relinquishTaskIdentity`activity 是否会将其任务标识符交给任务堆栈中在其之上的 activity。如果任务的根 activity 将该属性设置为“`true`”，则该任务会用其下一个 activity 的 intent 替换基本 intent。如果下一个 activity 也将该属性设置为“`true`”，则该 activity 会将基本 intent 让给其在同一任务中启动的其他 activity。系统会继续为每个 Activity 执行此过程，直至遇到某个 Activity 将该属性设置为“`false`”为止。默认值为“`false`”。如果将此属性设置为“`true`”，则 Activity 还可使用 `ActivityManager.TaskDescription` 更改**概览屏幕**中的标签、颜色和图标。
31. `resizeableActivity`指定应用是否支持**多窗口模式**。您可以在 `<activity>` 或 [`<application>`](https://developer.android.google.cn/guide/topics/manifest/application-element) 元素中设置此属性。

如果您将此属性设为 true，则用户可以在分屏模式和自由窗口模式下启动 Activity。如果您将此属性设为 false，则无法针对多窗口环境测试或优化应用。在应用了兼容模式的情况下，系统可能仍会将 Activity 置于多窗口模式中。将此属性设置为 true 也不能保证屏幕（例如画中画）中不会显示多窗口模式下的其他应用。因此，设置此标志并不能确保您的应用获得专属资源访问权限。

如果您的应用以 API 级别 24 或更高级别为目标平台，但您未指定此属性的值，则其值默认设为 true。

如果您的应用以 API 级别 31 或更高级别为目标平台，则此属性在小屏设备和大屏设备上的运作方式会有所不同：

* 大屏 (sw >= 600dp) 设备：所有应用都支持多窗口模式。该属性指示应用是否可以调整大小（而不是应用是否支持多窗口模式）。如果 `resizeableActivity="false"`，应用会在必要时进入兼容模式，以与显示屏尺寸相符。
* 小屏 (sw < 600dp) 设备：如果 `resizeableActivity="true"`，activity 的最小宽度和最小高度都在多窗口模式要求范围内，则应用支持多窗口模式。如果 `resizeableActivity="false"`，则无论 activity 的最小宽度和高度是多少，应用都不支持多窗口模式。

> **注意** ：设备制造商可以替换 API 级别 31 的行为。

该属性是 API 级别 24 中的新增属性。

> **注意** ：任务的根 activity 值会应用于任务中启动的所有其他 activity。也就是说，如果任务的根 Activity 可调整大小，系统会视为任务中的所有其他 Activity 均可调整大小。如果根 activity 不可调整大小，则任务中的其他 activity 也不可调整大小。

32. `android:screenOrientation`Activity 在设备上的显示方向。如果 Activity 是在**多窗口模式**下运行，则系统会忽略该属性。其值可以是下列任一字符串：


| “`unspecified`”      | 默认值。由系统选择方向。在不同设备上，系统使用的政策以及基于政策在特定上下文中所做的选择可能会有所差异。                                                                                                                                                     |
| ------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| “`behind`”           | 与 activity 堆栈中紧接其后的 activity 的方向相同。                                                                                                                                                                                                           |
| “`landscape`”        | 屏幕方向为横向（显示的宽度大于高度）。                                                                                                                                                                                                                       |
| “`portrait`”         | 屏幕方向为纵向（显示的高度大于宽度）。                                                                                                                                                                                                                       |
| “`reverseLandscape`” | 屏幕方向是与正常横向方向相反的横向。*在 API 级别 9 中引入。*                                                                                                                                                                                                 |
| “`reversePortrait`”  | 屏幕方向是与正常纵向方向相反的纵向。*在 API 级别 9 中引入。*                                                                                                                                                                                                 |
| “`sensorLandscape`”  | 屏幕方向为横向，但可根据设备传感器调整为正常或反向的横向。即使用户锁定基于传感器的旋转，系统仍可使用传感器。*在 API 级别 9 中引入。*                                                                                                                         |
| “`sensorPortrait`”   | 屏幕方向为纵向，但可根据设备传感器调整为正常或反向的纵向。即使用户锁定基于传感器的旋转，系统仍可使用传感器。*在 API 级别 9 中引入。*                                                                                                                         |
| “`userLandscape`”    | 屏幕方向为横向，但可根据设备传感器和用户首选项调整为正常或反向的横向。在 API 级别 18 中引入。                                                                                                                                                                |
| “`userPortrait`”     | 屏幕方向为纵向，但可根据设备传感器和用户首选项调整为正常或反向的纵向。 在 API 级别 18 中引入。                                                                                                                                                               |
| “`sensor`”           | 屏幕方向由设备方向传感器决定。显示方向取决于用户如何手持设备，它会在用户旋转设备时发生变化。但在默认情况下，一些设备不会旋转为所有四种可能的方向。如要支持所有这四种方向，请使用`"fullSensor"`。即使用户锁定基于传感器的旋转，系统仍可使用传感器。           |
| “`fullSensor`”       | 屏幕方向由使用 4 种方向中任一方向的设备方向传感器决定。 这与`"sensor"` 类似，不同之处在于无论设备在正常情况下使用哪种方向，该值均支持所有 4 种可能的屏幕方向（例如，一些设备正常情况下不使用反向纵向或反向横向，但其支持这些方向）。*在 API 级别 9 中引入。* |
| “`nosensor`”         | 确定屏幕方向时不考虑物理方向传感器。系统会忽略传感器，因此显示内容不会随用户手持设备的方向而旋转。                                                                                                                                                           |
| “`user`”             | 用户当前的首选方向。                                                                                                                                                                                                                                         |
| “`fullUser`”         | 如果用户锁定基于传感器的旋转，则其行为与`user` 相同，否则，其行为与 `fullSensor` 相同，并且支持所有 4 种可能的屏幕方向。 在 API 级别 18 中引入。                                                                                                             |
| “`locked`”           | 将方向锁定在其当前的任意旋转方向。在 API 级别 18 中引入。                                                                                                                                                                                                    |

> **注意** ：如果您声明其中一个横向或纵向值，则系统会将其视为对 Activity 运行方向的硬性要求。因此，您声明的值支持通过 Google Play 等服务进行过滤，这样只有支持 Activity 规定方向的设备才能使用您的应用。例如，如果您声明 `"landscape"`、`"reverseLandscape"` 或 `"sensorLandscape"`，则您的应用只能供支持横向方向的设备使用。不过，您还应通过 `<uses-feature>`元素明确声明，您的应用要求采用纵向还是横向方向。例如 `<uses-feature android:name="android.hardware.screen.portrait"/>`。这纯粹是 Google Play（以及其他支持过滤的服务）提供的一种过滤行为，平台本身并不能控制是否可在仅支持某种方向的设备上安装您的应用。

33. `android:showForAllUsers`当设备的当前用户不是启动 Activity 的用户时，是否要显示 Activity。您可将此属性设置为字面量值（`"true"` 或 `"false"`），或包含布尔值的资源或主题属性。该属性是 API 级别 23 中的新增属性。
34. `android:stateNotNeeded`能否在不保存 Activity 状态的情况下将其终止并成功重新启动 -“`true`”表示可在不考虑其之前状态的情况下重新启动，“`false`”表示需要之前状态。默认值为“`false`”。正常情况下，为保存资源而暂时关闭 Activity 前，系统会调用其 `onSaveInstanceState()` 方法。该方法会将 Activity 的当前状态存储在一个 `Bundle` 对象中，然后在 Activity 重启时将其传递给 `onCreate()`。如果将该属性设置为“`true`”，则系统可能不会调用 `onSaveInstanceState()`，并且会向 `onCreate()` 传递 `null`（而非 Bundle）- 这与 activity 首次启动时的情况完全相同。“`true`”设置可确保 Activity 能够在未保留状态时重启。例如，显示主屏幕的 Activity 可以使用该设置，确保系统不会在该 Activity 因某种原因而崩溃时将其移除。
35. `supportsPictureInPicture`指定 Activity 是否支持**画中画**显示。该属性是 API 级别 24 中的新增属性。
36. `android:taskAffinity`与 Activity 有着相似性的任务。从概念上讲，具有同一相似性的 Activity 归属同一任务（从用户的角度来看，则是归属同一“应用”）。任务的相似性由其根 Activity 的相似性确定。相似性确定两点内容 - Activity 更改父项后的任务（请参阅 `allowTaskReparenting` 属性），以及通过 `FLAG_ACTIVITY_NEW_TASK` 标志启动 Activity 时，用于容纳该 Activity 的任务。默认情况下，应用中的所有 Activity 都具有同一相似性。您可以设置该属性，以不同方式将其分组，甚至可以在同一任务内放置不同应用中定义的 Activity。如要指定 Activity 与任何任务均无相似性，请将其设置为空字符串。如果未设置该属性，则 Activity 会继承为应用设置的相似性（请参阅 `<application>` 元素的 `taskAffinity` 属性）。应用默认相似性的名称是在 `build.gradle` 文件中设置的**命名空间**。
37. `android:theme`对定义 Activity 总体主题的样式资源的引用。此属性会自动将 Activity 的场景设置为使用该主题（请参阅 `setTheme()`），并且还可引发 Activity 启动前的“启动”动画（以更加符合 Activity 的实际外观）。如果未设置该属性，则 Activity 会继承通过 `<application>` 元素的 `theme` 属性为应用整体设置的主题。如果同样未设置该属性，则使用默认系统主题。如需了解详情，请参阅**样式和主题**开发者指南。
38. `android:uiOptions`有关 Activity 界面的额外选项。必须是以下某个值。


| 值                           | 说明                                                                                                                                                                                                                                                                                                     |
| ------------------------------ | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `"none"`                     | 没有额外的界面选项。这是默认值。                                                                                                                                                                                                                                                                         |
| `"splitActionBarWhenNarrow"` | 当水平空间受限时（比如手机处于竖屏模式时），在屏幕底部添加一个栏，以显示应用栏（也称为操作栏）中的操作项。系统将应用栏分成顶部导航区和底部操作项栏，而非在屏幕顶部的应用栏中显示少量操作项。这可以确保操作项以及顶部的导航和标题元素均能获得适量空间。系统不会将菜单项拆分到两个栏中，它们始终一起出现。 |

39. `android:windowSoftInputMode`Activity 的主窗口与包含屏幕软键盘的窗口之间的交互方式。该属性的设置会影响两点内容：* 当 Activity 成为用户注意的焦点时软键盘的状态 - 隐藏还是可见。

* 对 Activity 主窗口所做的调整 - 是否将其尺寸调小，为软键盘腾出空间；或当软键盘遮盖部分窗口时，是否平移其内容以使当前焦点可见。

该设置必须是下表所列的其中一项值，或一个“`state...`”值加上一个“`adjust...`”值的组合。在任一组中设置多个值（例如，多个“`state...`”值）均会产生未定义的结果。各个值之间用竖线 (`|`) 分隔。例如：

```xml
<activityandroid:windowSoftInputMode="stateVisible|adjustResize" ... >
```

此处设置的值（“`stateUnspecified`”和“`adjustUnspecified`”除外）会替换主题中设置的值。


| 值                       | 说明                                                                                                                                                                                                                                                                                                           |
| -------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| “`stateUnspecified`”   | 不指定软键盘的状态（隐藏还是可见）。系统会选择合适的状态，或依赖主题中的设置。这是对软键盘行为的默认设置。                                                                                                                                                                                                     |
| “`stateUnchanged`”     | 当 Activity 转至前台时保留软键盘最后所处的任何状态，无论是可见还是隐藏。                                                                                                                                                                                                                                       |
| “`stateHidden`”        | 当用户选择 Activity 时（换言之，当用户确实是向前导航到 Activity，而不是因离开另一 Activity 而返回时）隐藏软键盘。                                                                                                                                                                                              |
| “`stateAlwaysHidden`”  | 当 activity 的主窗口有输入焦点时始终隐藏软键盘。                                                                                                                                                                                                                                                               |
| “`stateVisible`”       | 当用户选择 Activity 时（换言之，当用户确实是向前导航到 Activity，而不是因离开另一 Activity 而返回时）显示软键盘。                                                                                                                                                                                              |
| “`stateAlwaysVisible`” | 当窗口获得输入焦点时，会显示软键盘。                                                                                                                                                                                                                                                                           |
| “`adjustUnspecified`”  | 不指定 Activity 的主窗口是否通过调整尺寸为软键盘腾出空间，或者是否通过平移窗口内容以在屏幕上显示当前焦点。根据窗口的内容是否存在任何可滚动其内容的布局视图，系统会自动选择其中一种模式。如果存在这种视图，系统会调整窗口尺寸，前提是可通过滚动操作在较小区域内看到窗口的所有内容。这是对主窗口行为的默认设置。 |
| “`adjustResize`”       | 始终调整 Activity 主窗口的尺寸，以为屏幕上的软键盘腾出空间。                                                                                                                                                                                                                                                   |
| “`adjustPan`”          | 不通过调整 Activity 主窗口的尺寸为软键盘腾出空间。相反，窗口的内容会自动平移，使键盘永远无法遮盖当前焦点，以便用户始终能看到自己输入的内容。这通常不如调整窗口尺寸可取，因为用户可能需关闭软键盘才能进入被遮盖的窗口部分，并与之进行交互。                                                                     |

该属性是 API 级别 3 中的新增属性。

---

### 2.2 声明 intent 过滤器

- Intent 过滤器是 Android 平台的一项非常强大的功能。借助这项功能，我们不但可以根据显式请求启动 Activity，还可以根据隐式请求启动 Activity。例如，显式请求可能会告诉系统“在 Gmail 应用中启动‘发送电子邮件’Activity”，而隐式请求可能会告诉系统“在任何能够完成此工作的 Activity 中启动‘发送电子邮件’屏幕”。当系统界面询问用户使用哪个应用来执行任务时，这就是 intent 过滤器在起作用。
- 要使用此功能，您需要在 <activity> 元素中声明 <intent-filter> 属性。此元素的定义包括 <action> 元素，以及可选的 <category> 元素和/或 <data> 元素。这些元素组合在一起，可以指定 Activity 能够响应的 intent 类型。例如，以下代码段展示了如何配置一个发送文本数据并接收其他 Activity 的文本数据发送请求的 Activity：

```xml
    <activity android:name=".ExampleActivity" android:icon="@drawable/app_icon">
        <intent-filter>
            <action android:name="android.intent.action.SEND" />
            <category android:name="android.intent.category.DEFAULT" />
            <data android:mimeType="text/plain" />
        </intent-filter>
    </activity>
  
```

- 在此示例中，<action> 元素指定该 Activity 会发送数据。将 <category> 元素声明为 DEFAULT 可使 Activity 能够接收启动请求。<data> 元素指定此 Activity 可以发送的数据类型。以下代码段展示了如何调用上述 Activity：

```java
    // Create the text message with a string
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.setType("text/plain");
    sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
    // Start the activity
    startActivity(sendIntent);
  
```

- 如果我们打算构建一个独立的应用，不允许其他应用激活其 Activity，则不需要任何其他 intent 过滤器。我们不想让其他应用访问的 Activity 不应包含 intent 过滤器，我们可以自己使用显式 intent 启动它们。

### 2.3 声明权限

- 可以使用清单的 <activity> 标记来控制哪些应用可以启动某个 Activity。父 Activity 和子 Activity 必须在其清单中具有相同的权限，前者才能启动后者。如果我们为父 Activity 声明了 <uses-permission> 元素，则每个子 Activity 都必须具有匹配的 <uses-permission>元素。
- 例如，假设有一个应用想要使用一个名为 SocialApp 的应用在社交媒体上分享文章，则 SocialApp 本身必须定义调用它的应用所需具备的权限：

```xml
    <manifest>
    <activity android:name="...."
       android:permission=”com.google.socialapp.permission.SHARE_POST”

    />
  
```

然后，为了能够调用 SocialApp，您的应用必须匹配 SocialApp 清单中设置的权限：

```xml
    <manifest>
       <uses-permission android:name="com.google.socialapp.permission.SHARE_POST" />
    </manifest>
  
```

---

## 三、管理 Activity 生命周期

一个 Activity 在其生命周期中会经历多种状态。您可以使用一系列回调来处理状态之间的转换。下面几节将介绍这些回调。

### 3.1 onCreate()

您必须实现此回调，它会在系统创建您的 Activity 时触发。您的实现应该初始化 Activity 的基本组件：例如，您的应用应该在此处创建视图并将数据绑定到列表。最重要的是，您必须在此处调用 `setContentView()` 来定义 Activity 界面的布局。

`onCreate()` 完成后，下一个回调将是 `>onStart()`。

### 3.2 onStart()

`onCreate()` 退出后，Activity 将进入“已启动”状态，并对用户可见。此回调包含 Activity 进入前台与用户进行互动之前的最后准备工作。

### 3.3 onResume()

系统会在 Activity 开始与用户互动之前调用此回调。此时，该 Activity 位于 Activity 堆栈的顶部，并会捕获所有用户输入。应用的大部分核心功能都是在 `onResume()` 方法中实现的。

`onResume()` 回调后面总是跟着 `onPause()` 回调。

### 3.4 onPause()

当 Activity 失去焦点并进入“已暂停”状态时，系统就会调用 `onPause()`。例如，当用户点按“返回”或“最近使用的应用”按钮时，就会出现此状态。当系统为您的 Activity 调用 `onPause()` 时，从技术上来说，这意味着您的 Activity 仍然部分可见，但大多数情况下，这表明用户正在离开该 Activity，该 Activity 很快将进入“已停止”或“已恢复”状态。

如果用户希望界面继续更新，则处于“已暂停”状态的 Activity 也可以继续更新界面。例如，显示导航地图屏幕或播放媒体播放器的 Activity 就属于此类 Activity。即使此类 Activity 失去了焦点，用户仍希望其界面继续更新。

您**不**应使用 `<a href="https://developer.android.google.cn/reference/android/app/Activity#onPause()">onPause()</a>` 来保存应用或用户数据、进行网络呼叫或执行数据库事务。有关保存数据的信息，请参阅**保存和恢复 Activity 状态**。

`onPause()` 执行完毕后，下一个回调为 `onStop()`或 `onResume()`，具体取决于 Activity 进入“已暂停”状态后发生的情况。

### 3.5 onStop()

当 Activity 对用户不再可见时，系统会调用 `onStop()`。出现这种情况的原因可能是 Activity 被销毁，新的 Activity 启动，或者现有的 Activity 正在进入“已恢复”状态并覆盖了已停止的 Activity。在所有这些情况下，停止的 Activity 都将完全不再可见。

系统调用的下一个回调将是 `onRestart()`（如果 Activity 重新与用户互动）或者 `onDestroy()`（如果 Activity 彻底终止）。

### 3.6 onRestart()

当处于“已停止”状态的 Activity 即将重启时，系统就会调用此回调。`onRestart()` 会从 Activity 停止时的状态恢复 Activity。

此回调后面总是跟着 `onStart()`。

### 3.7 onDestroy()

系统会在销毁 Activity 之前调用此回调。

此回调是 Activity 接收的最后一个回调。通常，实现 `onDestroy()` 是为了确保在销毁 Activity 或包含该 Activity 的进程时释放该 Activity 的所有资源。

---

## 四、了解Activity生命周期

- 当用户浏览、退出和返回到我们创建的应用时，应用中的 `Activity` 实例会在其生命周期的不同状态间转换。`Activity` 类会提供许多回调，这些回调会让 Activity 知晓某个状态已经更改：系统正在创建、停止或恢复某个 Activity，或者正在销毁该 Activity 所在的进程。
- 在生命周期回调方法中，我们可以声明用户离开和再次进入 Activity 时 Activity 的行为方式。例如，如果我们正构建流媒体视频播放器，当用户切换至另一应用时，我们可能要暂停视频或终止网络连接。当用户返回时，我们可以重新连接网络并允许用户从同一位置继续播放视频。换言之，每个回调都支持您执行适合给定状态变更的特定作业。在合适的时间执行正确的作业，并妥善处理转换，这将提升应用的稳健性和性能。例如，良好的生命周期回调实现有助于防止应用出现以下问题：
  - 当用户在使用应用时接听来电，或切换至另一应用时崩溃。
  - 当用户未主动使用它时，消耗宝贵的系统资源。
  - 当用户离开应用并在稍后返回时，丢失用户的进度。
  - 当屏幕在横向和纵向之间旋转时，崩溃或丢失用户的进度。

### 4.1 Activity 生命周期概念

为了在 Activity 生命周期的各个阶段之间导航转换，Activity 类提供六个核心回调：`onCreate()`、`onStart()`、`onResume()`、`onPause()`、`onStop()` 和 `onDestroy()`。当 Activity 进入新状态时，系统会调用其中每个回调。图 1 是对此范例的直观展现。

![](https://developer.android.google.cn/guide/components/images/activity_lifecycle.png)

**图 1.** Activity 生命周期的简化图示。

当用户开始离开 Activity 时，系统会调用方法来销毁该 Activity。在某些情况下，此销毁只是部分销毁；Activity 仍然驻留在内存中（例如当用户切换至另一应用时），并且仍然可以返回到前台。如果用户返回到该 Activity，Activity 会从用户离开时的位置继续运行。除了少数例外，**应用在后台运行时会受到限制，无法启动 Activity**。

系统终止给定进程及其中 Activity 的可能性取决于当时 Activity 的状态。**Activity 状态和从内存中弹出** 会更详细地介绍状态与弹出漏洞之间的关系。

根据 Activity 的复杂程度，您可能不需要实现所有生命周期方法。但是，请务必了解每个方法，并实现能够确保应用按用户预期方式运行的方法，这非常重要。

在下一部分中，本文档将详细介绍用于处理状态间转换的回调。

### 4.2 生命周期回调

本部分介绍 Activity 生命周期中所用回调方法的相关概念及实现信息。

某些操作（例如调用 [`setContentView()`](https://developer.android.google.cn/reference/android/app/Activity#setContentView(int))）属于 Activity 生命周期方法本身。不过，用于实现依赖组件操作的代码应放在组件本身内。

#### 4.2.1 onCreate()

- 必须实现此回调，它会在系统首次创建 Activity 时触发。Activity 会在创建后进入“已创建”状态。在 `onCreate()` 方法中，您需执行基本应用启动逻辑，该逻辑在 Activity 的整个生命周期中只应发生一次。例如，[`onCreate()`](https://developer.android.google.cn/reference/android/app/Activity#onCreate(android.os.Bundle)) 的实现可能会将数据绑定到列表，将 Activity 与 [`ViewModel`](https://developer.android.google.cn/reference/androidx/lifecycle/ViewModel) 相关联，并实例化某些类作用域变量。此方法会接收 `savedInstanceState` 参数，后者是包含 Activity 先前保存状态的 `Bundle` 对象。如果 Activity 此前未曾存在，`Bundle` 对象的值为 null。
- 如果我们有一个生命周期感知型组件与我们的 Activity 生命周期相关联，该组件将收到 `ON_CREATE` 事件。系统将调用带有 @OnLifecycleEvent 注释的方法，以使我们的生命周期感知型组件可以执行已创建状态所需的任何设置代码。
- `onCreate()` 方法的以下示例显示执行 Activity 某些基本设置的一些代码，例如声明界面（在 XML 布局文件中定义）、定义成员变量，以及配置某些界面。在本示例中，系统通过将文件的资源 ID `R.layout.main_activity` 传递给 `setContentView()` 来指定 XML 布局文件。

```java
TextView textView;

// some transient state for the activity instance
String gameState;

@Override
public void onCreate(Bundle savedInstanceState) {
    // call the super class onCreate to complete the creation of activity like
    // the view hierarchy
    super.onCreate(savedInstanceState);

    // recovering the instance state
    if (savedInstanceState != null) {
        gameState = savedInstanceState.getString(GAME_STATE_KEY);
    }

    // set the user interface layout for this activity
    // the layout file is defined in the project res/layout/main_activity.xml file
    setContentView(R.layout.main_activity);

    // initialize member TextView so we can manipulate it later
    textView = (TextView) findViewById(R.id.text_view);
}

// This callback is called only when there is a saved instance that is previously saved by using
// onSaveInstanceState(). We restore some state in onCreate(), while we can optionally restore
// other state here, possibly usable after onStart() has completed.
// The savedInstanceState Bundle is same as the one used in onCreate().
@Override
public void onRestoreInstanceState(Bundle savedInstanceState) {
    textView.setText(savedInstanceState.getString(TEXT_VIEW_KEY));
}

// invoked when the activity may be temporarily destroyed, save the instance state here
@Override
public void onSaveInstanceState(Bundle outState) {
    outState.putString(GAME_STATE_KEY, gameState);
    outState.putString(TEXT_VIEW_KEY, textView.getText());

    // call superclass to save any view hierarchy
    super.onSaveInstanceState(outState);
}
```

- 除了定义 XML 文件，然后将其传递给 `setContentView()`，您还可以在 Activity 代码中新建 `View` 对象，并将新建的 `View` 插入到 `ViewGroup` 中，以构建视图层次结构。然后，将根 `ViewGroup` 传递给 `setContentView()` 以使用该布局。
- 现在我们的 Activity 并未处于“已创建”状态。`onCreate()` 方法完成执行后，Activity 进入“已开始”状态，系统会相继调用 `onStart()` 和 `onResume()` 方法。下一部分将介绍 `onStart()` 回调。

#### 4.2.2 onStart()

- 当 Activity 进入“已开始”状态时，系统会调用此回调。`onStart()` 调用使 Activity 对用户可见，因为应用会为 Activity 进入前台并支持互动做准备。例如，应用通过此方法来初始化维护界面的代码。
- 当 Activity 进入已开始状态时，与 Activity 生命周期相关联的所有生命周期感知型组件都将收到 `ON_START` 事件。
- `onStart()` 方法会非常快速地完成，并且与“已创建”状态一样，Activity 不会一直处于“已开始”状态。一旦此回调结束，Activity 便会进入“已恢复”状态，系统将调用 `onResume()` 方法。

#### 4.2.3 onResume()

- Activity 会在进入“已恢复”状态时来到前台，然后系统调用 `onResume()` 回调。这是应用与用户互动的状态。应用会一直保持这种状态，直到某些事件发生，让焦点远离应用。此类事件包括接到来电、用户导航到另一个 Activity，或设备屏幕关闭。
- 当 Activity 进入已恢复状态时，与 Activity 生命周期相关联的所有生命周期感知型组件都将收到 `ON_RESUME`事件。这时，生命周期组件可以启用在组件可见且位于前台时需要运行的任何功能，例如启动相机预览。
- 当发生中断事件时，Activity 进入“已暂停”状态，系统调用 `onPause()` 回调。
- 如果 Activity 从“已暂停”状态返回“已恢复”状态，系统将再次调用 `onResume()` 方法。因此，我们应实现 `onResume()`，以初始化在 `onPause()` 期间释放的组件，并执行每次 Activity 进入“已恢复”状态时必须完成的任何其他初始化操作。

以下是生命周期感知型组件的示例，该组件在收到 [`ON_RESUME`](https://developer.android.google.cn/reference/androidx/lifecycle/Lifecycle.Event#ON_RESUME) 事件时访问相机：

```java
public class CameraComponent implements LifecycleObserver {

    ...

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void initializeCamera() {
        if (camera == null) {
            getCamera();
        }
    }

    ...
}
```

- `LifecycleObserver`收到 ON_RESUME 事件后，上述代码便会初始化相机。然而，在多窗口模式下，即使处于“已暂停”状态，您的 Activity 也可能完全可见。例如，当用户处于多窗口模式，并点按另一个不包含 Activity 的窗口时，您的 Activity 将进入“已暂停”状态。如果您希望相机仅在应用处于“已恢复”（可见且在前台运行）状态时可用，请在收到上述 ON_RESUME 事件后初始化相机。如果您希望在 Activity 处于“已暂停”状态但可见时（例如在多窗口模式下）保持相机可用，应在收到 ON_START 事件后初始化相机。但请注意，若要让相机在 Activity 处于“已暂停”状态时可用，可能会导致系统在多窗口模式下拒绝其他处于“已恢复”状态的应用访问相机。有时可能有必要让相机在 Activity 处于“已暂停”状态时保持可用，但这样做实际可能会降低整体用户体验。请仔细考虑，生命周期的哪个阶段更适合在多窗口环境下控制共享系统资源。
- 无论选择在哪个构建事件中执行初始化操作，都需要在使用相应的生命周期事件来释放资源。如果在收到 ON_START 事件后初始化某些内容，就需要在收到 ON_STOP 事件后释放或终止相应内容。如果在收到 ON_RESUME 事件后初始化某些内容，就需要在收到 ON_PAUSE 事件后将其释放。
- 上述代码段将相机初始化代码放置在生命周期感知型组件中。我们也可以直接将此代码放入 Activity 生命周期回调（例如 `onStart()` 和 `onStop()`，但不建议这样做。通过将此逻辑添加到独立的生命周期感知型组件中，我们可以对多个 Activity 重复使用该组件，而无需复制代码。请参阅[使用生命周期感知型组件处理生命周期](https://developer.android.google.cn/topic/libraries/architecture/lifecycle)，了解如何创建生命周期感知型组件。

#### 4.2.4 onPause()

系统将此方法视为用户将要离开您的 Activity 的第一个标志（尽管这并不总是意味着 Activity 会被销毁）；此方法表示 Activity 不再位于前台（尽管在用户处于多窗口模式时 Activity 仍然可见）。使用 [`onPause()`](https://developer.android.google.cn/reference/android/app/Activity#onPause()) 方法暂停或调整当 [`Activity`](https://developer.android.google.cn/reference/android/app/Activity) 处于“已暂停”状态时不应继续（或应有节制地继续）的操作，以及您希望很快恢复的操作。Activity 进入此状态的原因有很多。例如：

* 如 **onResume()** 部分所述，某个事件会中断应用执行。这是最常见的情况。
* 在 Android 7.0（API 级别 24）或更高版本中，有多个应用在多窗口模式下运行。无论何时，都只有一个应用（窗口）可以拥有焦点，因此系统会暂停所有其他应用。
* 有新的半透明 Activity（例如对话框）处于开启状态。只要 Activity 仍然部分可见但并未处于焦点之中，它便会一直暂停。

当 Activity 进入已暂停状态时，与 Activity 生命周期相关联的所有生命周期感知型组件都将收到 [`ON_PAUSE`](https://developer.android.google.cn/reference/androidx/lifecycle/Lifecycle.Event#ON_PAUSE) 事件。这时，生命周期组件可以停止在组件未位于前台时无需运行的任何功能，例如停止相机预览。

我们还可以使用 `onPause()` 方法释放系统资源、传感器（例如 GPS）手柄，或当 Activity 暂停且用户不需要它们时仍然可能影响电池续航时间的任何资源。然而，正如上文的 onResume() 部分所述，如果处于多窗口模式，“已暂停”的 Activity 仍完全可见。因此，您应该考虑使用 onStop() 而非 onPause() 来完全释放或调整与界面相关的资源和操作，以便更好地支持多窗口模式。

响应 ON_PAUSE 事件的以下 [`LifecycleObserver`](https://developer.android.google.cn/reference/androidx/lifecycle/LifecycleObserver) 示例与上述 ON_RESUME 事件示例相对应，会释放在收到 ON_RESUME 事件后初始化的相机：

```java
public class JavaCameraComponent implements LifecycleObserver {

    ...

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void releaseCamera() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    ...
}
```

- `onPause()` 执行非常简单，而且不一定要有足够的时间来执行保存操作。因此，我们**不**应使用 `onPause()` 来保存应用或用户数据、进行网络调用或执行数据库事务。因为在该方法完成之前，此类工作可能无法完成。相反，我们应在 `onStop()` 期间执行高负载的关闭操作。
- onPause() 方法的完成并不意味着 Activity 离开“已暂停”状态。相反，Activity 会保持此状态，直到其恢复或变成对用户完全不可见。如果 Activity 恢复，系统将再次调用 onResume() 回调。如果 Activity 从“已暂停”状态返回“已恢复”状态，系统会让 Activity 实例继续驻留在内存中，并会在系统调用 onResume() 时重新调用该实例。在这种情况下，您无需重新初始化在任何回调方法导致 Activity 进入“已恢复”状态期间创建的组件。如果 Activity 变为完全不可见，系统会调用 onStop()。下一部分将介绍 onStop() 回调。

#### 4.2.5 onStop()

如果我们的 Activity 不再对用户可见，说明其已进入“已停止”状态，因此系统将调用 `onStop()` 回调。例如，当新启动的 Activity 覆盖整个屏幕时，可能会发生这种情况。如果 Activity 已结束运行并即将终止，系统还可以调用 `onStop()`。

当 Activity 进入已停止状态时，与 Activity 生命周期相关联的所有生命周期感知型组件都将收到 `ON_STOP` 事件。这时，生命周期组件可以停止在组件未显示在屏幕上时无需运行的任何功能。

在 `onStop()` 方法中，应用应释放或调整在应用对用户不可见时的无用资源。例如，应用可以暂停动画效果，或从精确位置更新切换到粗略位置更新。使用 `onStop()` 而非 `onPause()` 可确保与界面相关的工作继续进行，即使用户在多窗口模式下查看 Activity 也能如此。

我们还应使用 `onStop()` 执行 CPU 相对密集的关闭操作。例如，如果无法找到更合适的时机来将信息保存到数据库，可以在 `onStop()` 期间执行此操作。以下示例展示了 `onStop()` 的实现，它将草稿笔记内容保存到持久性存储空间中：

```java
@Override
protected void onStop() {
    // call the superclass method first
    super.onStop();

    // save the note's current draft, because the activity is stopping
    // and we want to be sure the current note progress isn't lost.
    ContentValues values = new ContentValues();
    values.put(NotePad.Notes.COLUMN_NAME_NOTE, getCurrentNoteText());
    values.put(NotePad.Notes.COLUMN_NAME_TITLE, getCurrentNoteTitle());

    // do this update in background on an AsyncQueryHandler or equivalent
    asyncQueryHandler.startUpdate (
            mToken,  // int token to correlate calls
            null,    // cookie, not used here
            uri,    // The URI for the note to update.
            values,  // The map of column names and new values to apply to them.
            null,    // No SELECT criteria are used.
            null     // No WHERE columns are used.
    );
}
```

- 请注意，上述代码示例直接使用 SQLite。但我们应该改用 Room，这是一个通过 SQLite 提供抽象层的持久性库。如需详细了解使用 Room 的好处，以及如何在应用中实现 Room，请参阅 [Room 持久性库](https://developer.android.google.cn/topic/libraries/architecture/room)指南。
- 当我们的 Activity 进入“已停止”状态时，`Activity` 对象会继续驻留在内存中：该对象将维护所有状态和成员信息，但不会附加到窗口管理器。Activity 恢复后，Activity 会重新调用这些信息。我们无需重新初始化在任何回调方法导致 Activity 进入“已恢复”状态期间创建的组件。系统还会追踪布局中每个 `View` 对象的当前状态，如果用户在 `EditText` 微件中输入文本，系统将保留文本内容，因此您无需保存和恢复文本。
- 进入“已停止”状态后，Activity 要么返回与用户互动，要么结束运行并消失。如果 Activity 返回，系统将调用 `onRestart()`。如果 `Activity` 结束运行，系统将调用 `onDestroy()`。下一部分将介绍 `onDestroy()` 回调。

#### 4.2.6 onDestroy()

销毁 Activity 之前，系统会先调用 `onDestroy()`。系统调用此回调的原因如下：

1. Activity 即将结束（由于用户彻底关闭 Activity 或由于系统为 Activity 调用 `finish()`-），或者
2. 由于配置变更（例如设备旋转或多窗口模式），系统暂时销毁 Activity

当 Activity 进入已销毁状态时，与 Activity 生命周期相关联的所有生命周期感知型组件都将收到 `ON_DESTROY` 事件。这时，生命周期组件可以在 Activity 被销毁之前清理所需的任何数据。

您应使用 `ViewModel` 对象来包含 Activity 的相关视图数据，而不是在您的 Activity 中加入逻辑来确定 Activity 被销毁的原因。如果因配置变更而重新创建 Activity，ViewModel 不必执行任何操作，因为系统将保留 ViewModel 并将其提供给下一个 Activity 实例。如果不重新创建 Activity，ViewModel 将调用 `onCleared()` 方法，以便在 Activity 被销毁前清除所需的任何数据。

我们可以使用 `isFinishing()` 方法区分这两种情况。

如果 Activity 即将结束，onDestroy() 是 Activity 收到的最后一个生命周期回调。如果由于配置变更而调用 onDestroy()，系统会立即新建 Activity 实例，然后在新配置中为新实例调用 `onCreate()`。

`onDestroy()` 回调应释放先前的回调（例如 `onStop()尚未释放的所有资源。

### 4.3 Activity 状态和从内存中弹出

系统会在需要释放 RAM 时终止进程；系统终止给定进程的可能性取决于当时进程的状态。反之，进程状态取决于在进程中运行的 Activity 的状态。表 1 展示了进程状态、Activity 状态以及系统终止进程的可能性之间的关系。


| 系统终止进程的可能性 | 进程状态                   | Activity 状态                |
| ---------------------- | ---------------------------- | ------------------------------ |
| 较小                 | 前台（拥有或即将获得焦点） | 已创建<br/>已开始<br/>已恢复 |
| 较大                 | 后台（失去焦点）           | 已暂停                       |
| 最大                 | 后台（不可见）             | 已停止                       |
|                      | 空                         | 已销毁                       |

表 1. 进程生命周期和 Activity 状态之间的关系

系统永远不会直接终止 Activity 以释放内存，而是会终止 Activity 所在的进程。系统不仅会销毁 Activity，还会销毁在该进程中运行的所有其他内容。。

用户还可以使用“设置”下的“应用管理器”来终止进程，以终止相应的应用。

如需详细了解一般进程，请参阅[进程和线程](https://developer.android.google.cn/guide/components/processes-and-threads)。如需详细了解进程生命周期如何与其中 Activity 的状态相关联，请参阅相应页面的[进程生命周期](https://developer.android.google.cn/guide/components/processes-and-threads#Lifecycle)部分。

### 4.4 保存和恢复瞬时界面状态

用户期望 Activity 的界面状态在整个配置变更（例如旋转或切换到多窗口模式）期间保持不变。但是，默认情况下，系统会在发生此类配置更改时销毁 Activity，从而清除存储在 Activity 实例中的任何界面状态。同样，如果用户暂时从应用切换到其他应用，并在稍后返回应用，他们也希望界面状态保持不变。但是，当用户离开应用且 Activity 停止时，系统可能会销毁该应用的进程。

当 Activity 因系统限制而被销毁时，我们应组合使用 `ViewModel`、`onSaveInstanceState()` 或本地存储来保留用户的瞬时界面状态。

本部分概述了实例状态的定义，以及如何实现 onSaveInstance() 方法，该方法是对 Activity 本身的回调。如果界面数据简单且轻量，例如原始数据类型或简单对象（比如 String），我们可以单独使用 onSaveInstanceState() 使界面状态在配置更改和系统启动的进程被终止时保持不变。但在大多数情况下，您应使用 ViewModel 和 onSaveInstanceState()，因为 onSaveInstanceState() 会产生序列化/反序列化费用。

### 4.5 实例状态

在某些情况下， Activity 会因正常的应用行为而被销毁，例如当用户按下返回按钮或您的 Activity 通过调用 `finish()` 方法发出销毁信号时。当 Activity 因用户按下返回按钮或因其自行结束而被销毁时，系统和用户对该 `Activity` 实例的概念将永远消失。在这些情况下，用户的期望与系统行为相匹配，我们无需完成任何额外工作。

但是，如果系统因系统限制（例如配置变更或内存压力）而销毁 Activity，虽然实际的 `Activity` 实例会消失，但系统会记住它曾经存在过。如果用户尝试回退到该 Activity，系统将使用一组描述 Activity 销毁时状态的已保存数据新建该 Activity 的实例。

系统用于恢复先前状态的已保存数据称为实例状态，是存储在 `Bundle` 对象中的键值对集合。默认情况下，系统使用 `Bundle` 实例状态来保存 Activity 布局中每个 `View` 对象的相关信息（例如在 `EditText` 微件中输入的文本值）。这样，如果您的 Activity 实例被销毁并重新创建，布局状态便会恢复为其先前的状态，且您无需编写代码。但是，您的 Activity 可能包含您要恢复的更多状态信息，例如追踪用户在 Activity 中的进程的成员变量。

> **注意** ：为了使 Android 系统恢复 Activity 中视图的状态，每个视图必须具有 `android:id` 属性提供的唯一 ID。

`Bundle` 对象并不适合保留大量数据，因为它需要在主线程上进行序列化处理并占用系统进程内存。如需保存大量数据，您应组合使用持久性本地存储、`onSaveInstanceState()` 方法和 `ViewModel` 类来保存数据.

### 4.6 使用 **onSaveInstanceState() 保存简单轻量的界面状态**

当 Activity 开始停止时，系统会调用 `onSaveInstanceState()` 方法，以便 Activity 可以将状态信息保存到实例状态 Bundle 中。此方法的默认实现保存有关 Activity 视图层次结构状态的瞬时信息，例如 `EditText` 微件中的文本或 `ListView` 微件的滚动位置。

如需保存 Activity 的其他实例状态信息，您必须替换 `onSaveInstanceState()`，并将键值对添加到您的 Activity 意外销毁时事件中所保存的 `Bundle` 对象中。替换 onSaveInstanceState() 时，如果您希望默认实现保存视图层次结构的状态，必须调用父类实现。例如：

```java
static final String STATE_SCORE = "playerScore";
static final String STATE_LEVEL = "playerLevel";
// ...

@Override
public void onSaveInstanceState(Bundle savedInstanceState) {
    // Save the user's current game state
    savedInstanceState.putInt(STATE_SCORE, currentScore);
    savedInstanceState.putInt(STATE_LEVEL, currentLevel);

    // Always call the superclass so it can save the view hierarchy state
    super.onSaveInstanceState(savedInstanceState);
}
```

> ⭐ **注意** ：当用户显式关闭 Activity 时，或者在其他情况下调用 `finish()` 时，系统不会调用 `onSaveInstanceState()`。

如需保存持久性数据（例如用户首选项或数据库中的数据），应在 Activity 位于前台时抓住合适机会。如果没有这样的时机，应在执行 [`onStop()`](https://developer.android.google.cn/reference/android/app/Activity#onStop()) 方法期间保存此类数据。

### 4.7 使用保存的实例状态恢复 Activity 界面状态

重建先前被销毁的 Activity 后，您可以从系统传递给 Activity 的 `Bundle` 中恢复保存的实例状态。`onCreate()` 和 `onRestoreInstanceState()` 回调方法均会收到包含实例状态信息的相同 `Bundle`。

因为无论系统是新建 Activity 实例还是重新创建之前的实例，都会调用 `onCreate()` 方法，所以在尝试读取之前，您必须检查状态 Bundle 是否为 null。如果为 null，系统将新建 Activity 实例，而不会恢复之前销毁的实例。

例如，以下代码段显示如何在 `onCreate()` 中恢复某些状态数据：

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState); // Always call the superclass first

    // Check whether we're recreating a previously destroyed instance
    if (savedInstanceState != null) {
        // Restore value of members from saved state
        currentScore = savedInstanceState.getInt(STATE_SCORE);
        currentLevel = savedInstanceState.getInt(STATE_LEVEL);
    } else {
        // Probably initialize members with default values for a new instance
    }
    // ...
}
```

我们可以选择实现系统在 `onStart()` 方法之后调用的 `onRestoreInstanceState()`，而不是在 `onCreate()` 期间恢复状态。仅当存在要恢复的已保存状态时，系统才会调用 `onRestoreInstanceState()`，因此无需检查 `Bundle` 是否为 null：

```java
public void onRestoreInstanceState(Bundle savedInstanceState) {
    // Always call the superclass so it can restore the view hierarchy
    super.onRestoreInstanceState(savedInstanceState);

    // Restore state members from saved instance
    currentScore = savedInstanceState.getInt(STATE_SCORE);
    currentLevel = savedInstanceState.getInt(STATE_LEVEL);
}
```

---

## 五、在 Activity 之间导航

在应用的生命周期中，应用很可能会多次进入和退出 Activity。例如，用户可以点按设备的返回按钮，或者 Activity 可能需要启动不同的 Activity。本部分介绍了实现成功的 Activity 转换需要了解的主题。这些主题包括从另一个 Activity 启动 Activity、保存 Activity 状态，以及恢复 Activity 状态。

### 5.1 从一个 Activity 启动另一个 Activity

Activity 通常需要在某个时刻启动另一个 Activity。例如，当应用需要从当前屏幕移动到新屏幕时，就会出现这种需求。

根据我们的 Activity 是否希望从即将启动的新 Activity 中获取返回结果，我们可以使用 `startActivity()` 或 `startActivityForResult()` 方法启动新 Activity。这两种方法都需要传入一个 `Intent` 对象。

`Intent` 对象指定我们要启动的具体 Activity，或描述您要执行的操作类型（系统为您选择相应的 Activity，该 Activity 甚至可以来自不同应用）。`Intent` 对象还可以携带由已启动的 Activity 使用的少量数据。如需详细了解 `Intent` 类，请参阅 [Intent 和 Intent 过滤器](https://developer.android.google.cn/guide/components/intents-filters)。

#### 5.1.1 startActivity()

如果新启动的 Activity 不需要返回结果，当前 Activity 可以通过调用 `startActivity()` 方法来启动它。

在自己的应用中工作时，通常只需启动已知 Activity。例如，以下代码段显示如何启动一个名为 `SignInActivity` 的 Activity。

```java
Intent intent = new Intent(this, SignInActivity.class);
startActivity(intent);
```

应用可能还希望使用 Activity 中的数据执行某些操作，例如发送电子邮件、短信或状态更新。在这种情况下，我们应用自身可能不具有执行此类操作所需的 Activity，因此可以改为利用设备上其他应用提供的 Activity 来执行这些操作。这便是 intent 的真正价值所在。我们可以创建一个 intent，对想执行的操作进行描述，系统会从其他应用启动相应的 Activity。如果有多个 Activity 可以处理 intent，用户可以选择要使用哪一个。例如，如果我们想允许用户发送电子邮件，可以创建以下 intent：

```java
Intent intent = new Intent(Intent.ACTION_SEND);
intent.putExtra(Intent.EXTRA_EMAIL, recipientArray);
startActivity(intent);
```

添加到 intent 中的 `EXTRA_EMAIL` extra 是一个字符串数组，其中包含电子邮件的收件人电子邮件地址。当电子邮件应用响应此 intent 时，该应用会读取 extra 中提供的字符串数组，并将该数组放入电子邮件撰写表单的“收件人”字段。在这种情况下，电子邮件应用的 Activity 会启动，并且当用户完成操作时，您的 Activity 会继续运行。

#### 5.1.2 startActivityForResult()

有时，您会希望在 Activity 结束时从 Activity 中获取返回结果。例如，您可以启动一项 Activity，让用户在联系人列表中选择收件人；当 Activity 结束时，系统将返回用户选择的收件人。为此，您可以调用 `startActivityForResult(Intent, int)` 方法，其中整数参数会标识该调用。此标识符用于消除来自同一 Activity 的多次 `startActivityForResult(Intent, int)` 调用之间的歧义。这不是全局标识符，不存在与其他应用或 Activity 冲突的风险。结果通过 `onActivityResult(int, int, Intent)` 方法返回。

当子级 Activity 退出时，它可以调用 `setResult(int)` 将数据返回到其父级。子级 Activity 必须始终提供结果代码，该结果代码可以是标准结果 `RESULT_CANCELED`、`RESULT_OK`，也可以是从 `RESULT_FIRST_USER` 开始的任何自定义值。此外，子级 Activity 可以根据需要返回包含它所需的任何其他数据的 `Intent` 对象。父级 Activity 使用 `onActivityResult(int, int, Intent)` 方法，以及父级 Activity 最初提供的整数标识符来接收信息。

如果子级 Activity 由于任何原因（例如崩溃）而失败，父级 Activity 将收到代码为 `RESULT_CANCELED` 的结果。

```java
public class MyActivity extends Activity {
     // ...

     static final int PICK_CONTACT_REQUEST = 0;

     public boolean onKeyDown(int keyCode, KeyEvent event) {
         if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
             // When the user center presses, let them pick a contact.
             startActivityForResult(
                 new Intent(Intent.ACTION_PICK,
                 new Uri("content://contacts")),
                 PICK_CONTACT_REQUEST);
            return true;
         }
         return false;
     }

     protected void onActivityResult(int requestCode, int resultCode,
             Intent data) {
         if (requestCode == PICK_CONTACT_REQUEST) {
             if (resultCode == RESULT_OK) {
                 // A contact was picked.  Here we will just display it
                 // to the user.
                 startActivity(new Intent(Intent.ACTION_VIEW, data));
             }
         }
     }
 }
```

#### 5.1.3 协调 Activity

当一个 Activity 启动另一个 Activity 时，它们都会经历生命周期转换。第一个 Activity 停止运行并进入“已暂停”或“已停止”状态，同时创建另一个 Activity。如果这些 Activity 共享保存到磁盘或其他位置的数据，必须要明确第一个 Activity 在创建第二个 Activity 之前并未完全停止。相反，启动第二个 Activity 的过程与停止第一个 Activity 的过程重叠。

生命周期回调的顺序已有明确定义，特别是当两个 Activity 在同一个进程（应用）中，并且其中一个要启动另一个时。以下是 Activity A 启动 Activity B 时的操作发生顺序：

1. Activity A 的 `onPause()` 方法执行。
2. Activity B 的 `onCreate()`、`onStart()` 和 `onResume()` 方法依次执行（Activity B 现在具有用户焦点）。
3. 然后，如果 Activity A 在屏幕上不再显示，其 `onStop()` 方法执行。

我们可以利用这种可预测的生命周期回调顺序管理从一个 Activity 到另一个 Activity 的信息转换。

## 附：从下一个activity中返回数据

### MainActivity.java

```java
    // launcher 用于 接收处理 从第二个Activity传回来的数据
    // 调用registerForActivityResult()方法用于处理传回来的数据；registerForActivityResult()需要传进来两个参数；第一个 ActivityResultContract，
    // 第二个参数需要传入一个回调 ActivityResultCallback，而我们的数据就是在这里进行处理的；最后返回一个 ActivityResultLauncher 对象
    // 调用 ActivityResultLauncher的 launch方法可以跳转到另一个Activity或者发起权限请求

    ActivityResultLauncher launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                String returnData = result.getData().getStringExtra("data_return");
                textView.setText(returnData);
            }
        }
    });
findViewById(R.id.btnStartActivityResult).setOnClickListener(this);

@Override
    public void onClick(View v) {
        switch (v.getId()){
                // 从下一个activity 返回数据
            case R.id.btnStartActivityResult:
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//                startActivityForResult(intent,1);
                launcher.launch(intent);
                break;
        }
    }

```

### SecondActivity.java

```java
public class SecondActivity extends AppCompatActivity {

    public static final String ACTION = "work.icu007.learnactivity.ACTION_START";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        TextView textView = findViewById(R.id.textView1);
        textView.setText(data);
        Button button = findViewById(R.id.btnBackData);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("data_return","I am from SecondActivity");
                setResult(RESULT_OK,intent1);
                finish();
            }
        });
    }
}
```

### Button 点击事件

1. 直接注册点击监听事件：
   - 如下：直接声明button然后注册点击监听；重写onClick()方法处理点击事件。

```java
        Button button = findViewById(R.id.btnBackData);
//        Button btnShowCount = findViewById(R.id.btnShowCount);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("data_return","I am from SecondActivity");
                setResult(RESULT_OK,intent1);
                finish();
            }
        });

```

1. 在xml中添加onClick属性

- 如下：先在Button按钮处添加onClick属性

```xml
<Button
        android:id="@+id/btnShowCount"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="showToast"
        android:text="Show Count"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.474"
        app:layout_constraintStart_toStartOf="parent"
        tools:ignore="MissingConstraints" />

```

- 在Activity中创建showToast()方法。
```java
 public void showToast(View view) {
        count++;
        Toast toast = Toast.makeText(this,"the count is " + count , Toast.LENGTH_SHORT);
        toast.show();
        }
```