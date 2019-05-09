# A Simple Banking System

## Overall descriptions

A simple banking system is to be developed with the intention of providing a generic, reusable system from which to develop more realistic systems. The requirements of the system are to offer a number of different accounts, each of which provides specific services to the customer. The following are all types of accounts that must be supported by the system: 

* Saver account 
* Junior account 
* Current account

When a customer joins the bank, they are required choose an account type to open, and must credit it with a minimum figure. A customer may open more than one type of account.

The following core functions are to be supported by the system: 
1. Open Account: In order to open an account, the customer must provide the following information: 

   * name 
   * address 
   * date of birth 
   * type of account to be opened 

   Only customers under the age of 16 may open a Junior account. To determine the credit status of a customer, the bank sends the customer's details to a Credit Agency, who then carries out a credit search. Provided that the customer has a satisfactory credit history, a new account is opened. Each account has a unique account number. A customer is also issued a separate personal identification number (PIN) for that account.

2. Deposit Funds: Funds may be deposited in an account provided that the depositor provides the appropriate account number. When funds are deposited, they are either cleared (the funds have been fully credited, e.g. cash), or un-cleared (transfer of funds is pending, e.g. Cheque). Cleared funds are immediately credited to the account.

3. Clear Funds: An external bank clearing system periodically clears un-cleared funds. Once cleared, they are immediately credited to the account.

4. Withdraw Funds: Customers may withdraw funds from an account by supplying their account number, an appropriate identification (in this case, their PIN), and the amount to be withdrawn. A customer cannot withdraw more funds than their limit permits. The type of account the funds are being drawn from determines a customer’s limit. In the case of Junior and Saver accounts, no withdrawal should result in a negative balance. In the case of a Current account, a customer may withdraw additional funds, up to, but not exceeding, their overdraft limit. For a withdrawal from a Saver account, a minimum period of notice (in days) must be given before any withdrawal can be made. On leaving the bank, all customers must have re-paid their debts.

5. Suspend Account: In some situations, accounts may be suspended and no further transactions may occur until the account is reinstated.

6. Close Account: A customer can choose to close their account provided that the balance has been cleared.

The sample solutions and activities outlined in this lecture are guidelines only. They are not complete. You need to complete your own development during lab2 - lab7.
Bear in mind that there is no absolute right answer – your solution may be perfectly appropriate.

## Requirements

* Epics
* User Stories: prioritisation and estimation
* Product backlog
* Paper prototype

## Design

![](https://ws1.sinaimg.cn/large/e67ea754ly1g2u15cnuedj20zd0qj78v.jpg)

将开发一个简单的银行系统，旨在提供一个通用的，可重复使用的系统，从中开发更真实的系统。系统的要求是提供许多不同的帐户，每个帐户都为客户提供特定的服务。以下是系统必须支持的所有类型的帐户：

 - Saver 帐户
 - Junior 账户
 - Current 账户

当客户加入银行时，他们需要选择要打开的帐户类型，并且必须以最小数字对其进行贷记。客户可以打开多种类型的帐户。

系统支持以下核心功能：

1. 开立账户：为了开立账户，客户必须提供以下信息：

   * 名称
   * 地址
   * 出生日期
   * 要打开的帐户类型

   只有16岁以下的客户才可以开设Junior帐户。为了确定客户的信用状态，银行将客户的详细信息发送给信贷机构，然后信贷机构进行信用搜索。如果客户具有令人满意的信用记录，则打开新帐户。每个帐户都有一个唯一的帐号。还向客户发出该帐户的单独个人识别码（PIN）。

2. 存款资金：如果存款人提供适当的账号，资金可以存入账户。当资金存入时，它们要么被清算（资金已经全额存入，例如现金），要么未清算（资金转移待决，例如支票）。清算后的资金会立即存入账户。

3. 清算基金：外部银行清算系统定期清算未清算的资金。一旦清算，它们将立即存入账户。

4. 提取资金：客户可以通过提供帐号，适当的身份证明（在这种情况下，他们的PIN）以及要提取的金额来从帐户中提取资金。客户不能提取超过其限额许可的资金。从中提取资金的账户类型决定了客户的限额。在Junior和Saver帐户的情况下，撤销不应导致负余额。对于经常账户，客户可以提取额外资金，最高可达但不超过其透支额度。对于从Saver账户中提款，必须在提款前提供最短通知期限（以天为单位）。离开银行后，所有客户都必须重新偿还债务。

5. 暂停帐户：在某些情况下，帐户可能会被暂停，并且在帐户恢复之前不会再进行任何交易。

6. 关闭帐户：如果已清除余额，客户可以选择关闭其帐户。

本讲座中概述的示例解决方案和活动仅供参考。他们不完整。您需要在lab2  -  lab7期间完成自己的开发。
请记住，没有绝对的正确答案 - 您的解决方案必须非常合适。