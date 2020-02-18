package gmail.roadtojob2019.brewery.service.impl;

import gmail.roadtojob2019.brewery.dto.*;
import gmail.roadtojob2019.brewery.entity.AuthInfoEntity;
import gmail.roadtojob2019.brewery.entity.UserEntity;
import gmail.roadtojob2019.brewery.exception.SuchCustomerAlreadyExistException;
import gmail.roadtojob2019.brewery.mapper.CustomerSignUpRequestMapper;
import gmail.roadtojob2019.brewery.mapper.OrderMapper;
import gmail.roadtojob2019.brewery.mapper.ProductMapper;
import gmail.roadtojob2019.brewery.mapper.ReviewMapper;
import gmail.roadtojob2019.brewery.repository.*;
import gmail.roadtojob2019.brewery.security.UserRole;
import gmail.roadtojob2019.brewery.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final AuthInfoRepository authInfoRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    private final PasswordEncoder passwordEncoder;

    private final CustomerSignUpRequestMapper customerSignUpRequestMapper;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final ReviewMapper reviewMapper;

    @Override
    @Transactional
    public void signUp(CustomerSignUpRequestDto request) throws SuchCustomerAlreadyExistException {
        if (authInfoRepository.findByLogin(request.getEmail()).isPresent()) {
            throw new SuchCustomerAlreadyExistException("User with email=" + request.getEmail() + " already exists");
        }
        saveUser(request);
    }

    private void saveUser(final CustomerSignUpRequestDto request) {
        final UserEntity userEntity = customerSignUpRequestMapper.sourceToDestination(request);
        userEntity.setUserRole(UserRole.CUSTOMER);
        final UserEntity savedUser = userRepository.save(userEntity);
        saveAuthInfo(request, savedUser);
    }

    private void saveAuthInfo(final CustomerSignUpRequestDto request, final UserEntity savedUser) {
        final AuthInfoEntity authInfoEntity = new AuthInfoEntity();
        authInfoEntity.setLogin(request.getEmail());
        authInfoEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        authInfoEntity.setUser(savedUser);
        authInfoRepository.save(authInfoEntity);
    }

    @Override
    public String signIn(CustomerSignInRequestDto request) {
        return "{\"id\":1}";
    }

    @Override
    @Transactional
    public List<ProductDto> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(productMapper::destinationToSource)
                .collect(Collectors.toList());
    }

    @Override
    public Long createOrder(OrderDto orderDto) {
        return orderRepository.save(orderMapper.orderDtoToOrder(orderDto)).getId();
    }

    @Override
    public Long createReview(ReviewDto reviewDto) {
        Long id = reviewRepository
                .save(reviewMapper.sourceToDestination(reviewDto))
                .getId();
        System.out.println(id);
        return id;

    }
}
