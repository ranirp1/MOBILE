### 1. Organisation of the Assignment

The assignment is a single assignment for the module worth 100% of the marks; please see the Blackboard site for the exact deadline. 
The final report submission is electronic via Blackboard and you will also give a brief demonstration.

该作业是该模块的单个作业，得分为 100%；请参阅 Blackboard 网站了解确切的截止日期。
最终报告以电子方式通过 Blackboard 提交，您还将进行简短的演示。

Workload and Teams 工作量和团队

**Learning outcomes**

After completing this assignment, you will be able to:
The assignment is intended to account for about 25 hours of each person’s work towards the module as a whole, within a team of 4 to 6 (as is typical in industry).

完成此作业后，您将能够： 该作业旨在占 4 到 6 人团队（行业中的典型情况）中每个人对整个模块的大约 25 个小时的工作时间。

It is important that you make clear what contribution each group member has made in your final report. 
It is required that each person contributes to each stage of the assignment, but it is up to each group 
to decide how to divide the work up between individuals. Buddy Check will also be used for Peer Review 
and to adjust individual marks within your team; typically this will not raise any mark by more than 10%, 
but may lower marks by more than 10%.

重要的是，您必须在最终报告中明确每个小组成员做出了哪些贡献。每个人都必须为任务的每个阶段做出贡献，但由每个小组决定如何在个人之间分配工作。 
Buddy Check 还将用于同行评审并调整团队内的个人分数；通常，这不会使任何分数提高超过 10%，但可能会使分数降低超过 10%。

**Deadlines**

The deadline is absolutely fixed and you should note that group assignment extensions are only given in very 
exceptional circumstances that affect every member of a group. You should therefore plan your work to aim at 
finishing your work at least a few days before the deadline – do not leave it until the deadline, just in case anything goes wrong, 
and you then find that you are late.

截止日期是绝对固定的，您应该注意，只有在影响小组每个成员的非常特殊的情况下才会延长小组作业。因此，你应该计划你的工作，
目标是至少在截止日期前几天完成你的工作——不要等到截止日期才完成，以防万一出现问题，然后你发现你迟到了。

**Material Provided*

Please see Lecture material, lab class examples and resources as detailed on the lecture notes.
N.B. no third-party code can be used in the assignment, except the code that has been explicitly 
provided in the lectures. For example, you are allowed to reuse the code given in the lecture/lab slides. 
However, you are not allowed to download any code from the Web or to use any other software that will 
perform a considerable part of the assignment. If in doubt, please ask on the assignment forum in Blackboard.

请参阅讲义中详细介绍的讲座材料、实验课示例和资源。注意：除讲座中明确提供的代码外，作业中不能使用任何第三方代码。例如，您可以重复使用讲座/实验幻灯片中给出的代码。
但是，您不得从网络下载任何代码或使用任何其他软件来执行大部分任务。如有疑问，请在 Blackboard 的作业论坛上提问。

### 2. Requirements

The learning objectives of this assignment are to ‘learn by doing’ and you will need to show
evidence that you have:

1. considered the user and usage through design, e.g. by showing hand drawn sketches 通过设计考虑用户和使用情况，例如通过展示手绘草图
2. built an app with a flexible sophisticated layout/interface; you should include Previews 构建了一个具有灵活复杂的布局/界面的应用程序；您应该包括预览，例如在你的报告中
   e.g. in your report
3. have used separation of concerns, i.e. including using MVVM with state 使用了关注点分离，即包括使用带有状态的 MVVM
4. developed local data storage, i.e. on device persistence 开发本地数据存储，即设备持久性
5. made use of the phone’s sensors, e.g. GPS/GeoLocation 利用手机的传感器，例如GPS/地理位置
6. made use of device services, e.g. camera 使用设备服务，例如相机
7. worked together as a group 作为一个团队一起工作

The requirements are to create a Geolocation aware Todo app. You will also need to choose the specific users/focus for the app in agreement with your team; 
this offers you some choice in the problem you tackle and the solution you develop.

要求是创建一个地理位置感知的 Todo 应用程序。您还需要与您的团队达成一致，选择应用程序的特定用户/焦点；这为您在解决问题和开发解决方案方面提供了一些选择。

#### 2.1 The problem

In general the problem is the area of task lists, also known as Todo Lists or Todos. In particular the focus is on Geolocation aware 
Todos - by recording the location when creating a Todo and also by allowing Todos to become active when a previously chosen location is visited.

一般来说，问题出在任务列表区域，也称为待办事项列表或待办事项。特别是重点关注地理位置感知的待办事项 - 通过在创建待办事项时记录位置，以及允许待办事项在访问先前选择的位置时激活。

You will, as part of your assignment, need to define a set of users and a more specific purpose/usage for their Todos. For example, you might focus on:

作为作业的一部分，您需要定义一组用户以及他们的待办事项的更具体的目的/用途。例如，您可能会关注：

1. Learner drivers studying for their driving test 学习驾驶者为驾驶考试而学习
2. PC owners building themselves their first PC and tracking components, build, etc. PC 用户自行构建第一台 PC 并跟踪组件、构建等。
3. A student planning and cooking a meal for a number of different guests  一名学生为多位不同的客人计划和烹饪餐点

I strongly recommend that at least one team member is very familiar with your target users/usage; 
e.g. do not propose ‘academics keeping track of conference submission deadlines’ or ‘racing car driver check list’ 
- since these are users and topics you would have to spend time learning more about.
- 
  我强烈建议至少一名团队成员非常熟悉您的目标用户/用途；例如不要提出“学术界跟踪会议提交截止日期”或“赛车手检查清单”——因为这些是你必须花时间了解更多的用户和主题。

#### 2.2 Required features

You will design, build and test your Mobile app for recording a single user’s Todos. The user must be able to create, update and 
delete their Todos (tasks).

您将设计、构建和测试用于记录单个用户的待办事项的移动应用程序。用户必须能够创建、更新和删除他们的 Todos（任务）。

The app must be built for mobile Android, using Kotlin and Compose1 and should run on Version 11, 12 or 13 (14 is also allowed but not supported). 
The minimum android version must be version 11 (or earlier).

该应用程序必须针对移动 Android 构建，使用 Kotlin 和 Compose1，并且应在版本 11、12 或 13 上运行（也允许但不支持 14）。最低 Android 版本必须为版本 11（或更早版本）。

The app will include image taking/uploading and also be geolocation aware for the purpose of recording location when creating Todos as well as location triggered Todos. 
For best marks, the app will allow2:

该应用程序将包括图像拍摄/上传，并且还具有地理位置感知功能，以便在创建待办事项以及位置触发的待办事项时记录位置。为了获得最佳分数，该应用程序将允许2：

1. Entering custom (empty) Todos including a title, description, priority (at least) 输入自定义（空）待办事项，包括标题、描述、优先级（至少）
2. Taking a picture (and choosing an existing picture) and adding it to a Todo, e.g. a
   photograph of where I parked my car - such as a picture of the floor number 拍摄一张照片（并选择一张现有照片）并将其添加到待办事项中，例如我停车位置的照片 - 例如楼层号码的照片
3. Showing typical tasks (for your users/focus) that a User can copy and fill in further,
   e.g. finding out if any dinner guests have food allergies or dietary restrictions 显示用户可以复制并进一步填写的典型任务（针对您的用户/焦点），例如查明晚餐客人是否有食物过敏或饮食限制
4. Subtasks to be held within a Todo, likely as a list of checkboxes, e.g. a checkbox
   against each dinner guest 待办事项中保存的子任务，可能作为复选框列表，例如针对每位晚餐客人的复选框
5. Recording the (latest known) location on creating a Todo, as well as time and date 记录创建待办事项时的（最新已知）位置以及时间和日期
6. Including a date/time ‘reminder’, e.g. ‘8th Nov at 12.30pm’ or ‘in 2 days’ 包括日期/时间“提醒”，例如“11 月 8 日中午 12 点半”或“2 天内”
7. A notification to be shown when the reminder time is reached 到达提醒时间时显示的通知
8. Picking a (geofenced) location from a map and attaching to a Todo; this should be
   lat/lon based and will need to include a ‘distance’ parameter (circle area) 从地图中选择一个（地理围栏）位置并附加到待办事项；这应该基于纬度/经度，并且需要包含“距离”参数（圆形区域）
9. A notification to be shown when the user arrives at the location of a Todo 当用户到达待办事项的位置时显示的通知
10. Notifications should allow a user to easily open the app 通知应允许用户轻松打开应用程序
11. Showing the current Todos ordered by priority, date/time and nearest (location) 显示按优先级、日期/时间和最近（位置）排序的当前待办事项
12. Users must be able to edit all the parts of a Todo 用户必须能够编辑 Todo 的所有部分用户必须能够编辑 Todo 的所有部分
13. Mark Todos as ‘done’ and view/recover ‘done’ Todos 将待办事项标记为“已完成”并查看/恢复“已完成”待办事项
14. Permanent deletion of ‘done’ Todos with confirmation 永久删除“已完成”待办事项并进行确认
15. Your own (extra) requirement - that you will need to highlight in your submitted report.
    The difficulty and appropriateness of your requirement will be considered when marking. 您自己的（额外）要求 - 您需要在提交的报告中突出显示。评分时将考虑您要求的难度和适当性。

Note: these requirements also have hidden/implied requirements that you are expected to discover yourself, 
e.g. you will need to derive how priority is represented, how many values there are, what you do if the user doesn’t choose a priority.

注意：这些要求还包含您需要自己发现的隐藏/暗示的要求，例如您需要推导出优先级的表示方式、有多少个值、如果用户不选择优先级您会做什么。

You should consider using a software engineering process, e.g. agile, to help you develop as a team, 
and also to prioritise and identify the key user stories.

您应该考虑使用软件工程流程，例如敏捷，帮助您作为一个团队进行开发，并确定优先级和确定关键用户故事。

#### 2.3 Demonstration

In week 11 during the labs on friday, you will give a 10 minute demonstration of your app to show it working. 
You are strongly encouraged to time yourself beforehand. This is an opportunity for you to ‘show off’ the interactive aspects 
of your app and you must include your own requirement as part of this. Note: You should have more than one device ready to 
demonstrate on, possibly including a virtual device. You will also be given feedback which you may then use to improve 
your final submission (report).

在第 11 周的周五实验期间，您将用 10 分钟演示您的应用程序以展示其工作原理。强烈建议您提前给自己计时。这是您“展示”应用程序交互方面的机会，您必须将自己的要求作为其中的一部分。
注意：您应该准备好多个设备来进行演示，可能包括虚拟设备。您还将获得反馈，然后您可以使用这些反馈来改进您的最终提交（报告）。

You will likely find that some parts of your app will only be visible through your report; also some may be added/updated after the demonstration.

您可能会发现应用程序的某些部分只能通过报告可见；演示后也可能会添加/更新一些内容。

In marking your demonstration (and app), we will be considering:

在标记您的演示（和应用程序）时，我们将考虑：

1. Requirement completeness 需求完整性
2. Robustness/reliability 坚固性/可靠性
3. Interface suitability and aesthetics 界面适宜性和美观性
4. How the app copes with portrait/landscape modes and switching to/from the app 应用程序如何处理纵向/横向模式以及切换到应用程序/从应用程序切换
5. The quality of the solution; a simplistic (functional) solution will attract fewer marks The quality of the solution; a simplistic (functional) solution will attract fewer marks

#### 2.4 Exemplar Code

You will include in your report your exemplar (best) code, limited to a maximum of 4 sides.

您将在报告中包含您的示例（最佳）代码，最多 4 面。

This exemplar (kotlin) code allows you to show off your ability and you should choose your code to represent different aspects of your solution. 
You may include up to 20% of non application, e.g. test code. You must use source code colouring, line numbers and name each listing. 
Take a screenshot (do not paste as text) and paste it in the document, e.g. as seen in the slides (but with line numbers):

此示例 (kotlin) 代码可让您展示自己的能力，您应该选择代码来代表解决方案的不同方面。您最多可以包含 20% 的非app内容，例如测试代码。您必须使用源代码颜色、行号并命名每个列表
。截取屏幕截图（不要粘贴为文本）并将其粘贴到文档中，例如如幻灯片中所示（但带有行号）：


Please note the Caption must include (for best marks): 请注意，标题必须包括（为了获得最佳分数）：
 
1. an alphabetic listing ‘letter’, e.g. ‘Listing A’ above allows feedback to simply refer to
A.99 (i.e. listing A, line 99) 按字母顺序排列的“字母”，例如上面的“清单 A”允许反馈简单地引用 A.99（即清单 A，第 99 行）
2. the location from right clicking the file in Android Studio → Copy Path/Reference →
Path from Content Root, to allow the marker to see more detail in your repository
when necessary 在 Android Studio 中右键单击文件→复制路径/引用→内容根目录中的路径的位置，以允许标记在必要时在存储库中查看更多详细信息
3. You should not (typically) include a whole file listing You should not (typically) include a whole file listing
4. You may choose to ‘fold’ parts of your code 您可以选择“折叠”部分代码
5. Standard code quality applies, e.g. appropriate comments, meaningful naming 标准代码质量适用，例如适当的注释，有意义的命名
6. Code should, when possible, explain itself and not need many comments, e.g. use
meaningful function/variables names 如果可能的话，代码应该能够自我解释，并且不需要太多注释，例如使用有意义的函数/变量名称

I strongly recommend you do not paste code directly into your document – it frequently creates issues when opened for marking – a screenshot is quicker and more reliable.

我强烈建议您不要将代码直接粘贴到文档中 - 打开进行标记时它经常会产生问题 - 屏幕截图更快、更可靠。


#### 2.5 Code Committed to the Team Repository

We will also be looking at the last commit (i.e. at the deadline time) to the team’s Git repository for marking. 
We may run code within a virtual device or a Nokia phone, typically where a demonstration has left us uncertain about the quality of the app, 
or inspecting code where we have concerns about the report being realistic. In general, we will prefer to base marking on the
demonstration and the report.

我们还将查看团队 Git 存储库的最后一次提交（即截止时间）以进行标记。我们可能会在虚拟设备或诺基亚手机中运行代码，通常是在演示让我们不确定应用程序的质量的情况下，
或者在我们担心报告是否真实的情况下检查代码。一般来说，我们更愿意根据演示和报告进行评分。

**N.B. Do not send files after the deadline that you forgot to commit.**

注意：不要在您忘记提交的截止日期之后发送文件。

Please note the following important points in regard to the use of the Git repository:

使用 Git 存储库时请注意以下要点：

1. This must be the Team repository (Gitlab) and no other will be accepted. Ensure you
   commit to your team repository — not your personal repository or another Git
   repository 这必须是团队存储库 (Gitlab)，不接受其他存储库。确保您提交到您的团队存储库，而不是您的个人存储库或其他 Git 存储库
2. All members should be making regular commits. In marking your work, we will be
   checking that you have been making regular commits — not just one or two commits
   or a sudden rush of commits just before the deadline 所有成员都应该定期做出承诺。在标记您的工作时，我们将检查您是否进行了定期提交，而不仅仅是一两次提交或在截止日期之前突然匆忙提交
3. You should attempt a clean install/run before your final commit 您应该在最终提交之前尝试全新安装/运行

I strongly suggest your team organises itself with a ‘soft’ deadline 1–2 hours (or more) before the hard deadline. 
After your soft deadline, make final checks to ensure everything is committed and runs properly. If you’ve forgotten anything, 
you’ll still have time to correct the mistake. Don’t use the period after the soft deadline to make any code changes.

我强烈建议您的团队在硬截止日期前 1-2 小时（或更长时间）安排一个“软”截止日期。在软截止日期之后，进行最终检查以确保一切都已提交并正常运行。
如果您忘记了什么，您仍然有时间纠正错误。不要使用软截止日期之后的时间进行任何代码更改。

