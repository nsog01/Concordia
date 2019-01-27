function yInt = CubicLagSplines(x, y, xInt)
    n = length(x);
    
    if xInt < x(3)
        % if x is within the first three data points
        for i = 1:3
            xMod(i) = x(i);
            yMod(i) = y(i);
        end
    elseif xInt > x(n - 2)
        % if x is within the last three data points
        j = 1;
        
        for i = n - 2:n
            xMod(j) = x(i);
            yMod(j) = y(i);
            j = j + 1;
        end
    elseif xInt >= x(3) && xInt <= x(n - 2)
        % if xInt is between the first and last three data points
        for i = n:-1:1 % from n to 1, incrementing by -1
            if xInt > x(i)
                iCount = i;
                break
            end
        end
        
        j = iCount -1;
        
        for i = 1:4
           xMod(i) = x(j);
           yMod(i) = y(j);
           j = j + 1;
        end
    end
    
    n = length(xMod);
    
    for i = 1:n
        L(i) = 1;
        
        for j = 1:n
           if j ~= i
              L(i) = L(i)*(xInt - xMod(j))/(xMod(i) - xMod(j)); 
           end
        end
    end
    
    yInt = sum(yMod.*L);
end









