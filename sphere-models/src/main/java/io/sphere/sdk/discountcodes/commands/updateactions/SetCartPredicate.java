package io.sphere.sdk.discountcodes.commands.updateactions;

import io.sphere.sdk.cartdiscounts.CartDiscountPredicate;
import io.sphere.sdk.commands.UpdateActionImpl;
import io.sphere.sdk.discountcodes.DiscountCode;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * {@include.example io.sphere.sdk.discountcodes.commands.DiscountCodeUpdateCommandTest#setCartPredicate()}
 */
public class SetCartPredicate extends UpdateActionImpl<DiscountCode> {
    @Nullable
    private final String cartPredicate;

    private SetCartPredicate(@Nullable final String cartPredicate) {
        super("setCartPredicate");
        this.cartPredicate = cartPredicate;
    }

    public static SetCartPredicate of(@Nullable final CartDiscountPredicate cartPredicate) {
        return new SetCartPredicate(Optional.ofNullable(cartPredicate).map(CartDiscountPredicate::toSphereCartPredicate).orElse(null));
    }

    @Nullable
    public String getCartPredicate() {
        return cartPredicate;
    }
}
