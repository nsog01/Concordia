function x = GaussJordan(a, b)
    ab = [a, b];
    [R, C] = size(ab); % maps the number of rows to R and the number of columns to C


    for r = 1:R
        currPivot = ab(r, r); % takes the diagonal elements of the matrix
        rowPivot = r; % current pivot's row
    
        % look for a row with a larger pivoting element
        for lrgR = r + 1:R
            % if larger element exists in the rth column, assign it as the pivot
            if ab(lrgR, r) ~= 0 && abs(ab(lrgR, r)) > abs(currPivot)
               currPivot = ab(lrgR, r);
               rowPivot = lrgR;
            end
        end
    
        % if a larger pivot row has been found, swap the rows
        if rowPivot ~= r
            tmpAb = ab(r, :);
            ab(r, :) = ab(rowPivot, :);
            ab(rowPivot, :) = tmpAb;
        end
        
        ab(r, :) = ab(r, :)/ ab(r, r); % normalize rth element of rth row
        
        for rPrime = 1:R
           % this applies when we're not in the rth row
           if rPrime ~= r
               ab(rPrime, r:C) = ab(rPrime, r:C) - (ab(rPrime, r) * ab(r, r:C));
           end
        end 
    end
    
    x = ab(:, C);
end