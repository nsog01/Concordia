function Xs = Bisection(Func, a, b, tolMax, iMax)
% tolerance => tolMax => (b-a)/2
Fa = Func(a);
Fb = Func(b);

if Fa*Fb > 0
    disp('Error: the function has the same sign at a and b');
else
    for i = 1:iMax
        Xnew = (a+b)/2; % solution of current iteration
        
        tolI = (b-a)/2; % current tolerance
        FXnew = Func(Xnew); % value of F(Xnew) for current iteration
        
        if FXnew == 0
            Xs = Xnew;
            break;
        elseif tolI < tolMax
            break;
        elseif Func(a)*FXnew < 0
            b = Xnew;
        else
            a = Xnew;
        end
        
        Xs = Xnew;
        
        if i == iMax
           fprintf('Solution was not obtained in the first %i iterations', iMax);
           Xs = Xnew;
        end
    end
end

end