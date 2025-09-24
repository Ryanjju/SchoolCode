# SchoolCode
Just a place to save my code I've wirtten for school

## Factorial Calculation
Let **n** be a number from the set of non-negative integers **ℕ₀**.  
The factorial of **n**, written as `n!`, is mathematically defined as:

- `0! = 1`
- `n! = 1 · 2 · … · n` for all n ≥ 1  

It can also be described algorithmically, either **iteratively** or **recursively**:

### Iterative Algorithm
Algorithm FactorialIterative(n):
Input: n ∈ ℕ₀
Output: n!

```
if n = 0 then
    return 1

result ← 1
for i ← 1 to n do
    result ← result * i

return result
```

### Recursive Algorithm
Algorithm FactorialRecursive(n):
Input: n ∈ ℕ₀
Output: n!

```
if n = 0 then
    return 1
else
    return n * FactorialRecursive(n - 1)
```

### Code
The whole code is in this [Repo](/Factorial/Factorial.java).
