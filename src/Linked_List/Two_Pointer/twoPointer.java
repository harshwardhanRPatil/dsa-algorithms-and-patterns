package Linked_List.Two_Pointer;

import Linked_List.ListNode;

import java.util.*;

public class twoPointer {

  public ListNode middleNode(ListNode head) {
    ListNode p = head;
    ListNode q = head;
    if (head.next == null) return head;
    while (q != null && q.next != null) {
      q = q.next.next;
      p = p.next;
    }

    return p;
  }

  public boolean isPalindrome(ListNode head) {

    if (head == null) return false;
    if (head.next == null) return true;
    ListNode p = head;
    ListNode q = head;
    while (q != null && q.next != null) {
      q = q.next.next;
      p = p.next;
    }
    if (q != null) {
      p = p.next;
    }
    ListNode Temp = reverse(p);
    ListNode secound = head;
    while (Temp != null) {
      if (secound.val != Temp.val) {
        return false;
      }
      secound = secound.next;
      Temp = Temp.next;
    }
    return true;
  }

  public ListNode reverse(ListNode head) {
    ListNode q = head;
    ListNode p = null;

    while (q != null) {
      ListNode r = p;
      p = q;
      q = q.next;
      p.next = r;
    }
    return p;
  }

  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode head = new ListNode(0);
    ;
    ListNode temp = head;
    while (list1 != null && list2 != null) {
      if (list1.val > list2.val) {
        temp.next = list2;
        list2 = list2.next;
      } else {
        temp.next = list1;
        list1 = list1.next;
      }
      temp = temp.next;
    }
    if (list1 != null) temp.next = list1;
    if (list2 != null) temp.next = list2;
    return head.next;
  }

  public boolean hasCycle(ListNode head) {
    if (head == null) return false;
    if (head.next == null) return false;
    ListNode p = head;
    ListNode q = head;
    while (q != null && q.next != null) {

      q = q.next.next;
      p = p.next;
      if (p == q) return true;
    }
    return false;
  }

  // this will work as the linknode is more then 2
  public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
  }

  public ListNode deleteNodeWithValue(ListNode start, int node) {
    if (start == null) return null;
    if (start.val == node) return start.next;
    ListNode head = start;
    while (head.next != null) {
      if (head.next.val == node) {
        head.next = head.next.next;
        break;
      }
      head = head.next;
    }
    return start;
  }

  public ListNode detectCycle(ListNode head) {
    if (head == null) return null;
    if (head.next == null) return null;
    ListNode p = head;
    ListNode q = head;
    while (q != null && q.next != null) {
      q = q.next.next;
      p = p.next;
      if (p == q) break;
    }
    if (q == null || q.next == null) return null;
    p = head;
    while (p != q) {
      p = p.next;
      q = q.next;
    }
    return p;
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) return null;
    head = reverse(head);
    if (n == 1) {
      head = head.next;
    } else {
      ListNode curr = head;
      for (int i = 1; i < n - 1; i++) {
        curr = curr.next;
      }
      curr.next = curr.next.next;
    }
    return reverse(head);
  }

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    Set<ListNode> set = new HashSet<>();

    while (headA != null) {
      set.add(headA);
      headA = headA.next;
    }

    while (headB != null) {
      if (set.contains(headB)) {
        return headB;
      }
      headB = headB.next;
    }

    return null;
  }

  public ListNode reverseBetween(ListNode head, int left, int right) {
    if (left == right) return head;
    if (head == null || head.next == null) return head;
    if (left == 1) return reverse(head, right);
    ListNode p = head;
    for (int i = 1; i < left - 1; i++) {
      p = p.next;
    }
    System.out.println(p.val);
    p.next = reverse(p.next, right - left + 1);

    return head;
  }

  public ListNode reverse(ListNode head, int index) {
    ListNode q = head;
    ListNode p = null;

    while (q != null && index > 0) {
      ListNode r = p;
      p = q;
      q = q.next;
      p.next = r;
      index--;
    }
    head.next = q;

    return p;
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int carry = 0;
    ListNode head = new ListNode(0);
    ListNode p = head;
    while (l1 != null || l2 != null || carry != 0) {
      int sum = carry;

      if (l1 != null) {
        sum += l1.val;
        l1 = l1.next;
      }
      if (l2 != null) {
        sum += l2.val;
        l2 = l2.next;
      }
      carry = sum / 10;
      p.next = new ListNode(sum % 10);
      p = p.next;
    }

    return head.next;
  }

  public ListNode partition(ListNode head, int x) {
    ListNode smallDummy = new ListNode(0);
    ListNode largeDummy = new ListNode(0);

    ListNode small = smallDummy;
    ListNode large = largeDummy;

    while (head != null) {
      if (head.val < x) {
        small.next = head;
        small = small.next;
      } else {
        large.next = head;
        large = large.next;
      }
      head = head.next;
    }

    // Important: avoid cycle
    large.next = null;

    // Join both lists
    small.next = largeDummy.next;

    return smallDummy.next;
  }

  public ListNode oddEvenList(ListNode head) {
    ListNode smallDummy = new ListNode(0);
    ListNode largeDummy = new ListNode(0);

    ListNode small = smallDummy;
    ListNode large = largeDummy;
    boolean indices = true;
    while (head != null) {
      if (indices) {
        small.next = head;
        small = small.next;

      } else {
        large.next = head;
        large = large.next;
      }
      head = head.next;
      indices = !indices;
    }

    // Important: avoid cycle
    large.next = null;

    // Join both lists
    small.next = largeDummy.next;

    return smallDummy.next;
  }

  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) return head;

    ListNode slow = head;
    ListNode fast = head;
    ListNode prev = null;

    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    prev.next = null;

    ListNode left = sortList(head);
    ListNode right = sortList(slow);

    return merge(left, right);
  }

  public ListNode merge(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;

    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        curr.next = l1;
        l1 = l1.next;
      } else {
        curr.next = l2;
        l2 = l2.next;
      }
      curr = curr.next;
    }

    if (l1 != null) curr.next = l1;
    if (l2 != null) curr.next = l2;

    return dummy.next;
  }
}
