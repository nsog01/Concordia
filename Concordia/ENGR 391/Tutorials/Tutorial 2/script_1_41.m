clear; clc;
n=input('Please enter the number of terms of the series desired:\n');
total=0;

for i=1:n
    total = total +(((-1)^(i-1))/(2*i -1));
end

num_pi = 4*total;
true_pi=pi;

true_percent_error = TPE(true_pi,num_pi);

fprintf('For n =%3i, the calculated value of pi is %9.5f\n',n, num_pi)

fprintf('The true percent error is %9.5e percent\n', true_percent_error)


