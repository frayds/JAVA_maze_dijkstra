# JAVA_maze_dijkstra
JAVA自定义迷宫查询路径(dijkstra算法)
## Synopsis 项目大纲
此项目的实现目标是在一个给定的迷宫中寻找通往终点的最短路径，其中迷宫是由一个.csv文件规定的。在迷宫中有两道传送门，红色的是入传送门，绿色的是出传送门。所有迷宫中的元素都可以用户自定义改动，鼠标点击迷宫空白处或者黑色方块可以添加或取消墙壁。Shift+鼠标点击改动红色传送门位置，ctrl+鼠标点击改动绿色传送门位置。
## Code Example 代码示例
核心寻路代码使用广度优先搜索(BFS),具体代码查阅./Assignment/src/assign/BFSFinder.java。
## Motivation 创作动机
此项目是Object Oriented Programming and software Design课程的课程设计,占总成绩的60%。
## Installation 如何安装
下载此项目，使用IDE打开Assignment文件夹或者在命令行窗口将文件目标路径定位到此文件夹。执行main.java函数，在出现图形界面之后，鼠标点击迷宫空白处或者黑色方块可以添加或取消墙壁。Shift+鼠标点击改动红色传送门位置，ctrl+鼠标点击改动绿色传送门位置。
## API Reference
网上参考学习了BFS算法实现过程的部分结果。
## Tests 项目运行效果
![](https://github.com/frayds/JAVA_maze_dijkstra/raw/master/demo_pictures/maze.png)
