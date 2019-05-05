package Chapter3Find;

import java.util.Random;

public class AVLTree {

    private Node root;

    class Node {
        private Node left, right;
        private int key;
        private int balance;
        public int degree;

        public Node(int key) {
            this.key = key;
            left = right = null;
        }

        public String toSString(Node node, String prefix) {
            if (node == null) {
                return "";
            } else {
                String indent = prefix + '\t';
                return String.format("%sNode ->%o; \n%s%s", prefix, node.key, toSString(node.left, indent), toSString(node.right, indent));
            }
        }
    }

    public void insert(int key) {
        root = insert(root, key, true);
    }

    private Node insert(Node node, int key, boolean isHigh) {
        if (node == null) {
            node = new Node(key);
            isHigh = true;
            return node;
        }
//        if (key == node.key) {
//            return;
//        }
        Node x1, x2;

        if (key < node.key) {
            node.left = insert(node.left, key, isHigh);
            if (isHigh) {
                switch (node.balance) {
                    case 1: {
                        node.balance = 0;
                        isHigh = false;
                        break;
                    }
                    case 0: {
                        node.balance = -1;
                        break;
                    }
                    case -1: {
                        x1 = node.left;
                        if (x1.balance == -1) {
                            node.left = x1.right;
                            x1.right = node;
                            node.balance = 0;
                            node = x1;
                        } else {
                            x2 = x1.right;
                            x1.right = x2.left;
                            x2.left = x1;
                            node.left = x2.right;
                            x2.right = node;

                            if (x2.balance == -1) {
                                node.balance = 1;
                            } else {
                                node.balance = 0;
                            }

                            if (x2.balance == 1) {
                                x1.balance = -1;
                            } else {
                                x1.balance = 0;
                            }
                            node = x2;
                        }
                        node.balance = 0;
                        isHigh = false;
                        break;
                    }
                }
            }


        }
        if (key > node.key) {
            node.right = insert(node.right, key, isHigh);

            if (isHigh) {
                switch (node.balance) {
                    case -1: {
                        node.balance = 0;
                        isHigh = false;
                        break;
                    }
                    case 0: {
                        node.balance = 1;
                        break;
                    }
                    case 1: {
                        x1 = node.right;
                        if (x1.balance == 1) {
                            node.right = x1.left;
                            x1.left = node;
                            node.balance = 0;
                            node = x1;
                        } else {
                            x2 = x1.left;
                            x1.left = x2.right;
                            x2.right = x1;
                            node.right = x2.left;
                            x2.left = node;

                            if (x2.balance == 1) {
                                node.balance = -1;
                            } else if (x2.balance == -1) {
                                x1.balance = 1;
                            } else {
                                node.balance = 0;
                            }
                            node = x2;
                        }

                        node.balance = 0;
                        isHigh = false;
                        break;
                    }
                }
            }
        }
        return node;
    }


    private void balanceLeft(Node node, boolean isLower) {
        switch (node.balance) {
            case -1: {
                node.balance = 0;
                break;
            }
            case 0: {
                node.balance = 1;
                isLower = false;
                break;
            }
            case 1: {
                Node x1 = node.right;
                int bal1 = x1.balance;

                if (bal1 >= 0) {
                    node.right = x1.left;
                    x1.left = node;

                    if (bal1 == 0) {
                        node.balance = 1;
                        x1.balance = -1;
                        isLower = false;
                    } else {
                        node.balance = 0;
                        x1.balance = -1;
                        isLower = false;
                    }
                    node = x1;
                } else {
                    Node x2 = x1.left;
                    int bal2 = x2.balance;

                    x1.left = x2.right;
                    x2.right = x1;
                    node.right = x2.left;
                    x2.left = node;

                    if (bal2 == 1) {
                        node.balance = -1;
                    } else if (bal2 == -1) {
                        x1.balance = 1;
                    } else {
                        x1.balance = 0;
                    }

                    node = x2;
                    x2.balance = 0;
                }
                break;
            }

        }
    }

    private void balanceRight(Node node, boolean isLower) {
        switch (node.balance) {
            case 1: {
                node.balance = 0;
                break;
            }
            case 0: {
                node.balance = -1;
                isLower = false;
                break;
            }
            case -1: {
                Node x1 = node.left;
                int bal1 = x1.balance;

                if (bal1 <= 0) {
                    node.left = x1.right;
                    x1.right = node;

                    if (bal1 == 0) {
                        node.balance = -1;
                        x1.balance = 1;
                        isLower = false;
                    } else {
                        node.balance = 0;
                        x1.balance = 0;
                    }
                    node = x1;
                } else {
                    Node x2 = x1.right;
                    int bal2 = x2.balance;

                    x1.right = x2.left;
                    x2.left = x1;
                    node.left = x2.right;
                    x2.right = node;

                    if (bal2 == -1) {
                        node.balance = 1;
                    } else if (bal2 == 1) {
                        x1.balance = -1;
                    } else {
                        x1.balance = 0;
                    }

                    node = x2;
                    x2.balance = 0;
                }
                break;
            }
        }
    }

    private void Del(Node node, boolean isLower, Node delNode) {
        if (node.right != null) {
            Del(node.right, isLower, delNode);
            if (isLower) {
                balanceRight(node, isLower);
            }
        } else {
            int key = node.key;
            node.key = delNode.key;
            delNode.key = key;
            delNode = node;
            node = node.left;
            isLower = true;
        }
    }

    private void delete(Node node, int key, boolean isLower) {
        if (node == null) {
            System.out.println("Insert key, dumb");
            return;
        }
        if (key < node.key) {
            delete(node.left, key, isLower);
            if (isLower) {
                balanceLeft(node, isLower);
            }
        } else if (key > node.key) {
            delete(node.right, key, isLower);
            if (isLower) {
                balanceRight(node, isLower);
            }
        } else {
            Node nodeToDel = node;
            if (nodeToDel.right == null) {
                node = nodeToDel.left;
                isLower = true;
            } else if (nodeToDel.left == null) {
                node = nodeToDel.right;
                isLower = true;
            } else {
                Del(nodeToDel.left, isLower, nodeToDel);

                if (isLower) {
                    balanceLeft(node, isLower);
                }
                nodeToDel = null;
            }


        }
    }

    public String toSString() {
        return String.format("%s", root.toSString(root, " "));
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Random pr = new Random();

        for (int i = 0; i < 8; i++) {
            tree.insert(Math.abs(pr.nextInt() % 5));
        }
//        System.out.println(tree.toSString());
    }
}
