// IAdd.aidl
package com.example.faisal.aidlserverapp;

/**
* Interface that will communicate to other service
* This interface should be same as the service app side and package name should also be same.
**/

interface IAdd
 {
   int add(int num1, int num2);

 }
