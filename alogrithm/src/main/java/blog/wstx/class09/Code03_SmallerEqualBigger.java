package blog.wstx.class09;

import blog.wstx.class03.SingleNode;

public class Code03_SmallerEqualBigger {


	public static SingleNode listPartition1(SingleNode head, int pivot) {
		if (head == null) {
			return head;
		}
		SingleNode cur = head;
		int i = 0;
		while (cur != null) {
			i++;
			cur = cur.next;
		}
		SingleNode[] nodeArr = new SingleNode[i];
		i = 0;
		cur = head;
		for (i = 0; i != nodeArr.length; i++) {
			nodeArr[i] = cur;
			cur = cur.next;
		}
		arrPartition(nodeArr, pivot);
		for (i = 1; i != nodeArr.length; i++) {
			nodeArr[i - 1].next = nodeArr[i];
		}
		nodeArr[i - 1].next = null;
		return nodeArr[0];
	}

	public static void arrPartition(SingleNode[] nodeArr, int pivot) {
		int small = -1;
		int big = nodeArr.length;
		int index = 0;
		while (index != big) {
			if (nodeArr[index].val < pivot) {
				swap(nodeArr, ++small, index++);
			} else if (nodeArr[index].val == pivot) {
				index++;
			} else {
				swap(nodeArr, --big, index);
			}
		}
	}

	public static void swap(SingleNode[] nodeArr, int a, int b) {
		SingleNode tmp = nodeArr[a];
		nodeArr[a] = nodeArr[b];
		nodeArr[b] = tmp;
	}

	public static SingleNode listPartition2(SingleNode head, int pivot) {
		SingleNode sH = null; // small head
		SingleNode sT = null; // small tail
		SingleNode eH = null; // equal head
		SingleNode eT = null; // equal tail
		SingleNode mH = null; // big head
		SingleNode mT = null; // big tail
		SingleNode next = null; // save next node
		// every node distributed to three lists
		while (head != null) {
			next = head.next;
			head.next = null;
			if (head.val < pivot) {
				if (sH == null) {
					sH = head;
					sT = head;
				} else {
					sT.next = head;
					sT = head;
				}
			} else if (head.val == pivot) {
				if (eH == null) {
					eH = head;
					eT = head;
				} else {
					eT.next = head;
					eT = head;
				}
			} else {
				if (mH == null) {
					mH = head;
					mT = head;
				} else {
					mT.next = head;
					mT = head;
				}
			}
			head = next;
		}
		// 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
		if (sT != null) { // 如果有小于区域
			sT.next = eH;
			eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
		}
		// 下一步，一定是需要用eT 去接 大于区域的头
		// 有等于区域，eT -> 等于区域的尾结点
		// 无等于区域，eT -> 小于区域的尾结点
		// eT 尽量不为空的尾巴节点
		if (eT != null) { // 如果小于区域和等于区域，不是都没有
			eT.next = mH;
		}
		return sH != null ? sH : (eH != null ? eH : mH);
	}

	public static void printLinkedList(SingleNode node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		SingleNode head1 = new SingleNode(7);
		head1.next = new SingleNode(9);
		head1.next.next = new SingleNode(1);
		head1.next.next.next = new SingleNode(8);
		head1.next.next.next.next = new SingleNode(5);
		head1.next.next.next.next.next = new SingleNode(2);
		head1.next.next.next.next.next.next = new SingleNode(5);
		printLinkedList(head1);
		// head1 = listPartition1(head1, 4);
		head1 = listPartition2(head1, 5);
		printLinkedList(head1);

	}

}
