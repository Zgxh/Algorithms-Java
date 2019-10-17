#### 1. 向量的模 - 向量的二范数
$$\|{\vec x}\|_2 = (|x_1|^2 + |x_2|^2 + \cdots + |x_D|^2)^{\frac{1}{2}}$$

#### 2.核函数
##### （1）线性核 Linear Kernel
$$\kappa(x,z) = <x, z> = x^T z$$

##### （2）高斯核 Gaussian Kernel
$$\kappa(x,z) = exp\{ \frac{-\|x-z\|^2}{2 \sigma^2} \}$$

##### （3）多项式核 Ploynomial Kernel
$$\kappa(x,z) = (<x,z> + b)^c$$

#### 3. KPCA中的核矩阵
##### （1）高维映射 $\phi(\cdot)$
$$x \in \mathbb{R}^{D_1} \rightarrow \phi (x) \in \mathbb{R}^{D_2}, 其中D_1 < D_2$$
##### （2）核矩阵
$$ K = \begin{bmatrix}
\phi(x_1)^T \phi(x_1) & \phi(x_1)^T \phi(x_2) & \cdots \phi(x_1)^T \phi(x_n)\\
\phi(x_2)^T \phi(x_1) & \phi(x_2)^T \phi(x_2) & \cdots \phi(x_2)^T \phi(x_n)\\
\vdots & \vdots & \vdots\\
\phi(x_n)^T \phi(x_1) & \phi(x_n)^T \phi(x_2) & \cdots \phi(x_n)^T \phi(x_n)
\end{bmatrix}$$
##### （3）高维映射的中心化
令：$$\overline{\phi}(x) = \frac{1}{n} \sum \limits_{i=1}^{n} \phi(x_i)$$
则中心化后的高维映射：
$$\widehat \phi(x) = \phi(x) - \overline{\phi}(x) $$
对于**中心化前**的核矩阵：
a. 列均值：
$$J = [\overline{\phi}(x)^T\phi(x_1), \overline{\phi}(x)^T\phi(x_2), \cdots, \overline{\phi}(x)^T\phi(x_n)] \in \mathbb{R}^{n\times 1}$$
b. 总均值：
$$E = \frac{1}{n} \sum\limits_{i=1}^{n} \overline{\phi}(x)^T\phi(x_i) = \overline{\phi}(x)^T\overline{\phi}(x)$$
对应的**中心化后**的核矩阵$\widehat K$：（取任意位置$i, j$）
$$
\begin{aligned}
\widehat K_{i,j} &= \widehat\phi(x_i)^T \widehat\phi(x_j) \\
&= (\phi(x_i) - \overline{\phi}(x))^T (\phi(x_j) - \overline{\phi}(x)) \\
&= \phi(x_i)^T\phi(x_j) - \overline{\phi}(x)^T\phi(x_j) - \phi(x_i)^T\overline{\phi}(x) + \overline{\phi}(x)^T\overline{\phi}(x)\\
&= K_{i,j} - J_j - J_i + E
\end{aligned}
$$
