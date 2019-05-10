package sxy.algorithm.nowcoder.advanced.chapter04;

/**
 * 左右部分的最大差值
 * 
 * 给定一数组Arr，从中切一刀分成两半（两边必须都有数），求左右部分的最大值的差值|Max左-Max右|最大是多少，要求时间复杂度O(N)，额外空间复杂度O
 * (1).
 * 
 * 思路：
 * 
 * 1.每次都遍历找左右两边的最大值，时间复杂度为O(N^2)
 * 
 * 2.使用前缀、后缀最大值的辅助数组，时间复杂度降为O(N)，但额外空间复杂度为O(N)
 * 
 * 3.终极做法：将数组遍历一遍取得最大值Max，则答案就是Max-Math.min(arr[0],[arr.length-1])，时间复杂度O(N)，
 * 额外空间复杂度O(1).
 * 
 * @author Kevin
 * 
 */

public class MaxDifference {

}
