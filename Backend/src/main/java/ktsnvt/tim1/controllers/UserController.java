package ktsnvt.tim1.controllers;

import ktsnvt.tim1.DTOs.LoginDTO;
import ktsnvt.tim1.DTOs.UserDTO;
import ktsnvt.tim1.exceptions.EntityAlreadyExistsException;
import ktsnvt.tim1.exceptions.EntityNotFoundException;
import ktsnvt.tim1.exceptions.EntityNotValidException;
import ktsnvt.tim1.model.User;
import ktsnvt.tim1.security.TokenUtils;
import ktsnvt.tim1.services.UserDetailsServiceImpl;
import ktsnvt.tim1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class UserController {

	@Autowired
    AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
    private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO,
										HttpServletResponse response) throws AuthenticationException {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getEmail(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getEmail());
            return new ResponseEntity<String>(tokenUtils.generateToken(details), HttpStatus.OK);
        }
        catch (BadCredentialsException ex) {
            return new ResponseEntity<String>("Invalid email or password", HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody User user) {
		try{
			return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
		}catch (EntityAlreadyExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping()
    public ResponseEntity<Object> editUser(@Valid @RequestBody UserDTO user){
		try{
			return new ResponseEntity<>(userService.editUser(user), HttpStatus.OK);
		}catch (EntityNotValidException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
    }

}
